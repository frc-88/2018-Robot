package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.RobotMap;
import org.usfirst.frc.team88.robot.commands.IntakeControl;
import org.usfirst.frc.team88.robot.util.SharpIR;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * <pre>
 * Kyle Hackett
 * 
 * Intake Haiku v2.
 * 
 * The green wheels spin fast
 * The cube is now ours to use
 * And now we will win
 * 
 * v3.
 * The green wheels spin fast
 * The yellow cube is now ours
 * And now we will win
 * </pre>
 */
public class Intake extends Subsystem {

	private static final double TIMEOUT = 0;
	private static final double MAX_INTAKE_SPEED = 0.8;
	private static final double MAX_EJECT_SPEED = 1.0;
	private static final double MIN_SPEED = 0.5;
	private static final double MAX_DISTANCE = 16;
	private static final double MIN_DISTANCE = 5;
	private static final double MAX_DIFF = 2.0;
	private static final int TIMEOUTMS = 0;

	
	private TalonSRX leftSide, rightSide;
	private SharpIR leftDistanceSensor, rightDistanceSensor;
	private DoubleSolenoid upDown;

	public Intake() {
		leftSide = new TalonSRX(RobotMap.intakeLeft);
		rightSide = new TalonSRX(RobotMap.intakeRight);
		rightSide.setInverted(true);

		leftSide.configPeakCurrentLimit(15, TIMEOUTMS);
		leftSide.configPeakCurrentDuration(0, TIMEOUTMS);
		leftSide.configContinuousCurrentLimit(15, TIMEOUTMS);
		leftSide.enableCurrentLimit(true);

		rightSide.configPeakCurrentLimit(15, TIMEOUTMS);
		rightSide.configPeakCurrentDuration(0, TIMEOUTMS);
		rightSide.configContinuousCurrentLimit(15, TIMEOUTMS);
		rightSide.enableCurrentLimit(true);

		
		leftDistanceSensor = new SharpIR(RobotMap.intakeLeftIR);
		rightDistanceSensor = new SharpIR(RobotMap.intakeRightIR);

		 upDown = new DoubleSolenoid(RobotMap.intakeSolenoidIn, RobotMap.intakeSolenoidOut);
		 upDown.set(Value.kForward);
	}

	// Sets intake wheel speed
	public void wheelSpeed(double speed) {
		double leftSpeed = speed * MAX_INTAKE_SPEED;
		double rightSpeed = speed * MAX_INTAKE_SPEED;
		double leftDistance = leftDistanceSensor.getDistance();
		double rightDistance = rightDistanceSensor.getDistance();

		
		if ((leftDistance > MIN_DISTANCE) && (leftDistance < MAX_DISTANCE) && (rightDistance > MIN_DISTANCE)
				&& (rightDistance < MAX_DISTANCE) && (speed < 0)) {
			double slowSide = (MAX_INTAKE_SPEED
					- ((MAX_INTAKE_SPEED - MIN_SPEED) * Math.abs(leftDistance - rightDistance) / MAX_DIFF)) * speed;

			// Right Side Closer
			if (leftDistance > rightDistance) {
				rightSpeed = slowSide;
			}

			// Left Side Closer
			if (rightDistance > leftDistance) {
				leftSpeed = slowSide;
			}
		} else if (speed > 0) {
			leftSpeed = speed * MAX_EJECT_SPEED;
			rightSpeed = speed * MAX_EJECT_SPEED;
		}

		rightSide.set(ControlMode.PercentOutput, rightSpeed, TIMEOUT);
		leftSide.set(ControlMode.PercentOutput, leftSpeed, TIMEOUT);
	}

	public boolean haveCube() {
		Preferences prefs = Preferences.getInstance();
		double haveCubeDistance = prefs.getDouble("HaveCubeDistance", MIN_DISTANCE); 
		
		return (getLeftDistance() < haveCubeDistance) && (getRightDistance() < haveCubeDistance);
	}

	// Gets the cube distance from left sensor
	private double getLeftDistance() {
		return leftDistanceSensor.getDistance();
	}

	// Gets the cube distance from right sensor
	private double getRightDistance() {
		return rightDistanceSensor.getDistance();
	}

	// Pneumaticly puts cradle up
	public void cradleUp() {
		upDown.set(Value.kForward);
	}

	// Pneumaticly puts cradle down
	public void cradleDown() {
		upDown.set(Value.kReverse);
	}

	public void updateDashboard() {
		SmartDashboard.putNumber("Intake Left Sensor Distance", leftDistanceSensor.getDistance());
		SmartDashboard.putNumber("Intake Left Sensor Voltage", leftDistanceSensor.getAverageVoltage());
		SmartDashboard.putNumber("Intake Right Sensor Distance", rightDistanceSensor.getDistance());
		SmartDashboard.putNumber("Intake Right Sensor Voltage", rightDistanceSensor.getAverageVoltage());

		SmartDashboard.putNumber("Intake Left Motor Current", leftSide.getOutputCurrent());
		SmartDashboard.putNumber("Intake Left Motor Voltage", leftSide.getMotorOutputVoltage());
		SmartDashboard.putNumber("Intake Right Motor Current", rightSide.getOutputCurrent());
		SmartDashboard.putNumber("Intake Right Motor Voltage", rightSide.getMotorOutputVoltage());

		 SmartDashboard.putBoolean("Intake Cradle Up?", upDown.get() == Value.kForward);

		SmartDashboard.putBoolean("Intake Have Cube?", haveCube());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new IntakeControl());
	}

}
