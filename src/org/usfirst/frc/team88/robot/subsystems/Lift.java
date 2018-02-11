package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.RobotMap;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * <pre>
 * high up in the air
 * placing cubes like we don't care
 * scale tips scoring points
 *  * </pre>
 */
public class Lift extends Subsystem {
	private static final int SLOTIDX = 0;
	private static final int TIMEOUTMS = 0;
	private final static double RAMPRATE = .30;
	private final static double MAX_SPEED = 13000;
	private final static int CRUISE_VELOCITY = 13000;
	private final static int ACCELERATION = 6000;
	private final static double P = 0.0;
	private final static double I = 0.0;
	private final static double D = 0.0;
	private final static double F = 1023 / MAX_SPEED;
	
	public final static int POS_BOTTOM = 0;
	public final static int POS_SWITCH = 0;
	public final static int POS_LOW_SCALE = 0;
	public final static int POS_MID_SCALE = 0;
	public final static int POS_HI_SCALE = 0;
	public final static int DISTANCE_THRESHOLD = 50;
	
	
	private static final int FORWARDLIMIT = 1023;
	private static final int REVERSELIMIT = 0;
	// TODO Add constants for lift positions
	private TalonSRX master;
	private TalonSRX follower;

	public Lift() {
		master = new TalonSRX(RobotMap.liftMaster);
		follower = new TalonSRX(RobotMap.liftFollower);

		/* analog signal with no wrap-around (0-3.3V) */
		master.configSelectedFeedbackSensor(FeedbackDevice.Analog, SLOTIDX, TIMEOUTMS);
		
		/* eFeedbackNotContinuous = 1, subValue/ordinal/timeoutMs = 0 */
		master.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, TIMEOUTMS);

		master.config_kP(SLOTIDX, P, TIMEOUTMS);
		master.config_kI(SLOTIDX, I, TIMEOUTMS);
		master.config_kD(SLOTIDX, D, TIMEOUTMS);
		master.config_kF(SLOTIDX, F, TIMEOUTMS);
		master.configNominalOutputForward(0.0, TIMEOUTMS);
		master.configNominalOutputReverse(0.0, TIMEOUTMS);
		master.configPeakOutputForward(+1.0, TIMEOUTMS);
		master.configPeakOutputReverse(-1.0, TIMEOUTMS);
		master.configNeutralDeadband(0.04, TIMEOUTMS);
		master.configClosedloopRamp(RAMPRATE, TIMEOUTMS);
		master.setSensorPhase(false);
		master.setNeutralMode(NeutralMode.Brake);

		master.configMotionCruiseVelocity(CRUISE_VELOCITY, TIMEOUTMS);
		master.configMotionAcceleration(ACCELERATION, TIMEOUTMS);
		
		master.configForwardSoftLimitThreshold(FORWARDLIMIT, TIMEOUTMS);
		master.configForwardSoftLimitEnable(true, TIMEOUTMS);

		master.configReverseSoftLimitThreshold(REVERSELIMIT, TIMEOUTMS);
		master.configReverseSoftLimitEnable(true, TIMEOUTMS);

		// TODO configure for position based closed loop control using
		//      motion magic capability of TalonSRX
		//
		// Interesting links:
		// https://github.com/CrossTheRoadElec/Phoenix-Documentation/raw/master/Talon%20SRX%20Victor%20SPX%20-%20Software%20Reference%20Manual.pdf
		// http://www.ctr-electronics.com/downloads/api/java/html/com/ctre/phoenix/motorcontrol/can/BaseMotorController.html#configMotionCruiseVelocity-int-int-
		// http://www.ctr-electronics.com/downloads/api/java/html/com/ctre/phoenix/motorcontrol/can/BaseMotorController.html#configMotionAcceleration-int-int-
		// https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/tree/master/Java/MotionMagic
		
		follower.follow(master);

	}

	public void levitate(double velocity) {
		master.set(ControlMode.PercentOutput, velocity);
	}
	
	public void setPosition(double position) {
		master.set(ControlMode.MotionMagic, position);
	}

	public void updateDashboard() {
		//waiting to be fixed
		SmartDashboard.putNumber("Lift/Master/Position", master.getSelectedSensorPosition(SLOTIDX));
		SmartDashboard.putNumber("Lift/Master/Velocity", master.getSelectedSensorVelocity(SLOTIDX));
		SmartDashboard.putNumber("Lift/Master/Error", master.getClosedLoopError(SLOTIDX));
		SmartDashboard.putNumber("Lift/Master/Current", master.getOutputCurrent());
		SmartDashboard.putNumber("Lift/Master/Voltage", master.getMotorOutputVoltage());
		SmartDashboard.putNumber("Lift/Follower/Current", follower.getOutputCurrent());
		SmartDashboard.putNumber("Lift/Follower/Voltage", follower.getMotorOutputVoltage());
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public boolean onTarget(int target) {
		return Math.abs(master.getSelectedSensorPosition(SLOTIDX) - target) > DISTANCE_THRESHOLD;
	}
}
