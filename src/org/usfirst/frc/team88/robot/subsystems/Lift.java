package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.RobotMap;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Rookies
 * TODO Write haiku
 */
public class Lift extends Subsystem {
	private static final int SLOTIDX = 0;
	private static final int TIMEOUTMS = 0;
	private static final int FORWARDLIMIT = 1023;
	private static final int REVERSELIMIT = 0;
	// TODO Add constants for lift positions
	private TalonSRX master;
	private VictorSPX follower;

	public Lift() {
		master = new TalonSRX(RobotMap.liftMaster);
		follower = new VictorSPX(RobotMap.liftFollower);

		/* analog signal with no wrap-around (0-3.3V) */
		master.configSelectedFeedbackSensor(FeedbackDevice.Analog, SLOTIDX, TIMEOUTMS);
		
		/* eFeedbackNotContinuous = 1, subValue/ordinal/timeoutMs = 0 */
		master.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, TIMEOUTMS);
		
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
		
		follower.set(ControlMode.Follower, RobotMap.liftMaster);

	}

	public void levitate(double velocity) {
		master.set(ControlMode.PercentOutput, velocity);
	}

	public void updateDashboard() {
		//waiting to be fixed
		SmartDashboard.putNumber("Lift/Position: ", master.getSelectedSensorPosition(SLOTIDX));
		SmartDashboard.putNumber("Lift/Velocity: ", master.getSelectedSensorVelocity(SLOTIDX));
		SmartDashboard.putNumber("Lift/Error: ", master.getClosedLoopError(SLOTIDX));
		SmartDashboard.putNumber("Lift/Master Current", master.getOutputCurrent());
		SmartDashboard.putNumber("Lift/Master Voltage", master.getMotorOutputVoltage());
		SmartDashboard.putNumber("Lift/Follower Current", follower.getOutputCurrent());
		SmartDashboard.putNumber("Lift/Follower Voltage", follower.getMotorOutputVoltage());
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
