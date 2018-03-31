package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.RobotMap;
import org.usfirst.frc.team88.robot.commands.ArmBasicControl;
import org.usfirst.frc.team88.robot.commands.LiftBasicControl;
import org.usfirst.frc.team88.robot.commands.LiftMove;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sam R., Brodie B.
 * 
 */
public class Arm extends Subsystem {
	private static final boolean BASIC_CONTROL = true;

	private static final int POS_DOWN_DFT = 0;
	private static final int POS_START_OFFSET = 1700;
	private static final int POS_UP_OFFSET = 2000;
	
	private static final int SLOTIDX = 0;
	private static final int TIMEOUTMS = 0;
	private static final double RAMPRATE = .30;
	private static final double MAX_SPEED = 50; // TODO
	private static final int CRUISE_VELOCITY = 50; // TODO
	private static final int ACCELERATION = 500; // TODO
	private static final double P = 0.0; // TODO
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

		initQuadrature();
		master.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, SLOTIDX, TIMEOUTMS);

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

		master.configPeakCurrentLimit(35, TIMEOUTMS);
		master.configPeakCurrentDuration(0, TIMEOUTMS);
		master.configContinuousCurrentLimit(30, TIMEOUTMS);
		master.enableCurrentLimit(true);

		master.setSensorPhase(false);
		master.setNeutralMode(NeutralMode.Brake);

		master.configMotionCruiseVelocity(CRUISE_VELOCITY, TIMEOUTMS);
		master.configMotionAcceleration(ACCELERATION, TIMEOUTMS);

		setPositionValues();
	}

	public void basicMotion(double input) {
		master.set(ControlMode.PercentOutput, input);
	}

	private void setPositionValues() {
		Preferences prefs = Preferences.getInstance();
		int armBottom = prefs.getInt("ArmBottom", POS_DOWN_DFT); 
		
		if (posDown != armBottom) {
			posDown = armBottom;
			posStart = posDown + POS_START_OFFSET;
			posUp = posDown + POS_UP_OFFSET;
		}
	}
	
	public void setPosition(double target){
		position = target;
	}
	
	public int getPosition(){
		return master.getSelectedSensorPosition(SLOTIDX);
	}
	public boolean isUp(){
		return master.getSelectedSensorPosition(SLOTIDX) > posStart;
	}
	
	public boolean isDown(){
		return master.getSelectedSensorPosition(SLOTIDX) < posDown - 10 
				|| master.getSelectedSensorPosition(SLOTIDX) > posDown + 10;
	}
	
	public boolean isStart(){
		return master.getSelectedSensorPosition(SLOTIDX) < posStart - 10 
				|| master.getSelectedSensorPosition(SLOTIDX) > posStart + 10;
	}
	
	public void goToUp() {
		master.set(ControlMode.MotionMagic, posUp);
	}
	
	public void goToDown() {
		master.set(ControlMode.MotionMagic, posDown);
	}
	
	public void goToStart() {
		master.set(ControlMode.MotionMagic, posStart);
	}
	
	
	
	/**
	 * Below is from CTRE example found here:
	 * https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java/MagEncoder%20(Absolute)/src/org/usfirst/frc/team217/robot/Robot.java
	 * 
	 * Seed the quadrature position to become absolute. This routine also
	 * ensures the travel is continuous.
	 */
	public void initQuadrature() {
		/* get the absolute pulse width position */
		int pulseWidth = master.getSensorCollection().getPulseWidthPosition();

//		/*
//		 * if there is a discontinuity in our measured range, subtract one half
//		 * rotation to remove it
//		 */
//		if (kDiscontinuityPresent) {
//
//			/* calculate the center */
//			int newCenter;
//			newCenter = (kBookEnd_0 + kBookEnd_1) / 2;
//			newCenter &= 0xFFF;
//
//			/*
//			 * apply the offset so the discontinuity is in the unused portion of
//			 * the sensor
//			 */
//			pulseWidth -= newCenter;
//		}

		/* mask out the bottom 12 bits to normalize to [0,4095],
		 * or in other words, to stay within [0,360) degrees */
		pulseWidth = pulseWidth & 0xFFF;

		/* save it to quadrature */
		master.getSensorCollection().setQuadraturePosition(pulseWidth, TIMEOUTMS);

	}

	
	public void updateDashboard() {
		SmartDashboard.putNumber("Arm Position(Absolute)", master.getSensorCollection().getPulseWidthPosition());
		SmartDashboard.putNumber("Arm Position(QuadRel)", master.getSensorCollection().getQuadraturePosition());
		SmartDashboard.putNumber("Arm Velocity(QuadRel)", master.getSensorCollection().getQuadratureVelocity());

		
		SmartDashboard.putNumber("Arm Position(Relative)", master.getSelectedSensorPosition(SLOTIDX));
		SmartDashboard.putNumber("Arm Velocity", master.getSelectedSensorVelocity(SLOTIDX));
		SmartDashboard.putNumber("Arm Error", master.getClosedLoopError(SLOTIDX));
		SmartDashboard.putNumber("Arm Current", master.getOutputCurrent());
		SmartDashboard.putNumber("Arm Voltage", master.getMotorOutputVoltage());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		if (BASIC_CONTROL) {
			setDefaultCommand(new ArmBasicControl());
		} else {
			setDefaultCommand(null);
		}
	}
}
