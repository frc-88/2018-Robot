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
	private TalonSRX liftTalon;
	private VictorSPX rightwing;

	public Lift() {
		liftTalon = new TalonSRX(RobotMap.liftTalon);
		rightwing = new VictorSPX(RobotMap.rightwing);

		/* analog signal with no wrap-around (0-3.3V) */
		liftTalon.configSelectedFeedbackSensor(FeedbackDevice.Analog, SLOTIDX, TIMEOUTMS);
		
		/* eFeedbackNotContinuous = 1, subValue/ordinal/timeoutMs = 0 */
		liftTalon.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, TIMEOUTMS);
		
		liftTalon.configForwardSoftLimitThreshold(FORWARDLIMIT, TIMEOUTMS);
		liftTalon.configForwardSoftLimitEnable(true, TIMEOUTMS);

		liftTalon.configReverseSoftLimitThreshold(REVERSELIMIT, TIMEOUTMS);
		liftTalon.configReverseSoftLimitEnable(true, TIMEOUTMS);

		// TODO configure for position based closed loop control using
		//      motion magic capability of TalonSRX
		//
		// Interesting links:
		// https://github.com/CrossTheRoadElec/Phoenix-Documentation/raw/master/Talon%20SRX%20Victor%20SPX%20-%20Software%20Reference%20Manual.pdf
		// http://www.ctr-electronics.com/downloads/api/java/html/com/ctre/phoenix/motorcontrol/can/BaseMotorController.html#configMotionCruiseVelocity-int-int-
		// http://www.ctr-electronics.com/downloads/api/java/html/com/ctre/phoenix/motorcontrol/can/BaseMotorController.html#configMotionAcceleration-int-int-
		// https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/tree/master/Java/MotionMagic
		
		rightwing.set(ControlMode.Follower, RobotMap.liftTalon);

	}

	public void levitate(double velocity) {
		liftTalon.set(ControlMode.PercentOutput, velocity);
	}

	public void updateDashboard() {
		//waiting to be fixed
		SmartDashboard.putNumber("Lift/Position: ", liftTalon.getSelectedSensorPosition(SLOTIDX));
		SmartDashboard.putNumber("Lift/Velocity: ", liftTalon.getSelectedSensorVelocity(SLOTIDX));
		SmartDashboard.putNumber("Lift/Error: ", liftTalon.getClosedLoopError(SLOTIDX));
		SmartDashboard.putNumber("Lift/Talon Current", liftTalon.getOutputCurrent());
		SmartDashboard.putNumber("Lift/Talon Voltage", liftTalon.getMotorOutputVoltage());
		SmartDashboard.putNumber("Lift/Victor Current", rightwing.getOutputCurrent());
		SmartDashboard.putNumber("Lift/Victor Voltage", rightwing.getMotorOutputVoltage());
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
