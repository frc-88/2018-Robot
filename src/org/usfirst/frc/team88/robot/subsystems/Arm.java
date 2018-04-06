package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.RobotMap;
import org.usfirst.frc.team88.robot.commands.ArmBasicControl;
import org.usfirst.frc.team88.robot.commands.ArmMove;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sam R., Brodie B.
 * new technology
 * behold our new found power
 * as we score backwards
 */
public class Arm extends Subsystem {
	private static final boolean BASIC_CONTROL = false;

	private static final int POS_START_OFFSET = 1550;
	private static final int POS_UP_OFFSET = 2000;
	
	private static final int SLOTIDX = 0;
	private static final int TIMEOUTMS = 0;
	private static final double RAMPRATE = .30;
	private static final double MAX_SPEED = 100;
	private static final int CRUISE_VELOCITY = 100; // TODO
	private static final int ACCELERATION = 1000; // TODO
	private static final double P = 0.5; // TODO
	private static final double I = 0.0; // TODO - probably 0
	private static final double D = 0.0; // TODO
	private static final double F = (1023 / MAX_SPEED) * 0.9;

	private TalonSRX master;
	
	private int posUp;
	private int posStart;
	private int posDown;

	private double position;
	
	public Arm() {
		master = new TalonSRX(RobotMap.armMotor);

		master.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, SLOTIDX, TIMEOUTMS);

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

		master.configPeakCurrentLimit(15, TIMEOUTMS);
		master.configPeakCurrentDuration(0, TIMEOUTMS);
		master.configContinuousCurrentLimit(14, TIMEOUTMS);
		master.enableCurrentLimit(true);

		master.setSensorPhase(true);
		master.setInverted(true);
		master.setNeutralMode(NeutralMode.Brake);

		master.configMotionCruiseVelocity(CRUISE_VELOCITY, TIMEOUTMS);
		master.configMotionAcceleration(ACCELERATION, TIMEOUTMS);

		setPositionValues();
	}

	private void setPositionValues() {
		//int armBottom = (int) master.configGetParameter(ParamEnum.eReverseSoftLimitThreshold, 0, TIMEOUTMS); 
		int armBottom = 981;
		
		posDown = armBottom;
		posStart = armBottom + POS_START_OFFSET;
		posUp = armBottom + POS_UP_OFFSET;
	}
	
	public void basicMotion(double input) {
		master.set(ControlMode.PercentOutput, input);
	}

	public void gotoPosition() {
		master.set(ControlMode.MotionMagic, position);
	}

	public void setPositionToUp() {
		position = posUp;
	}
	
	public void setPositionToDown() {
		position = posDown;
	}
	
	public void setPositionToStart() {
		position = posStart;
	}
	
	public int getPosition(){
		return master.getSelectedSensorPosition(SLOTIDX);
	}

	public boolean isUp(){
		return master.getSelectedSensorPosition(SLOTIDX) > posStart;
	}
	
	public boolean isDown(){
		return master.getSelectedSensorPosition(SLOTIDX) > posDown - 10 
				&& master.getSelectedSensorPosition(SLOTIDX) < posDown + 10;
	}
	
	public boolean isStart(){
		return master.getSelectedSensorPosition(SLOTIDX) > posStart - 10 
				&& master.getSelectedSensorPosition(SLOTIDX) < posStart + 10;
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber("Arm Position", master.getSelectedSensorPosition(SLOTIDX));
		SmartDashboard.putNumber("Arm Target", position);
		SmartDashboard.putNumber("Arm Velocity", master.getSelectedSensorVelocity(SLOTIDX));
		SmartDashboard.putNumber("Arm Error", master.getClosedLoopError(SLOTIDX));
		SmartDashboard.putNumber("Arm Current", master.getOutputCurrent());
		SmartDashboard.putNumber("Arm Voltage", master.getMotorOutputVoltage());
		
		SmartDashboard.putNumber("Arm posUp", posUp);
		SmartDashboard.putNumber("Arm posStart", posStart);
		SmartDashboard.putNumber("Arm posDown", posDown);
		
		SmartDashboard.putBoolean("Arm Up?", isUp());
		SmartDashboard.putBoolean("Arm Start?", isStart());
		SmartDashboard.putBoolean("Arm Down?", isDown());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		if (BASIC_CONTROL) {
			setDefaultCommand(new ArmBasicControl());
		} else {
			setDefaultCommand(new ArmMove());
		}
	}
}
