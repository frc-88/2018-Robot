package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.RobotMap;
import org.usfirst.frc.team88.robot.commands.IntakeControl;
import org.usfirst.frc.team88.robot.util.SharpIR;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
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
	private static final boolean DYNAMIC = false;
	private static final double TIMEOUT = 0;
	private static final double MAX_SPEED = 0.75;
	private static final double MIN_SPEED = 0.40;
	private static final double MAX_DISTANCE = 16;
	private static final double MIN_DISTANCE = 4;
	private static final double MAX_DIFF = 5.0;

	private TalonSRX leftSide, rightSide;
	private SharpIR leftDistanceSensor, rightDistanceSensor;
	private DoubleSolenoid upDown;

	public Intake() {
		leftSide = new TalonSRX(RobotMap.intakeLeft);
		rightSide = new TalonSRX(RobotMap.intakeRight);

		leftDistanceSensor = new SharpIR(RobotMap.intakeLeftIR);
		rightDistanceSensor = new SharpIR(RobotMap.intakeRightIR);

		// upDown = new DoubleSolenoid(RobotMap.intakeSolenoidIn,
		// RobotMap.intakeSolenoidOut);
		// upDown.set(Value.kOff);
	}

	// Sets intake wheel speed
	@SuppressWarnings("unused")
	public void wheelSpeed(double speed) {
		double leftSpeed = speed * MAX_SPEED;
		double rightSpeed = speed * MAX_SPEED;
		double leftDistance = leftDistanceSensor.getDistance();
		double rightDistance = rightDistanceSensor.getDistance();

		if ((leftDistance > MIN_DISTANCE) && (leftDistance < MAX_DISTANCE) && (rightDistance > MIN_DISTANCE)
				&& (rightDistance < MAX_DISTANCE) && (speed < 0) && DYNAMIC) {
			double slowSide = (MAX_SPEED
					- ((MAX_SPEED - MIN_SPEED) * Math.abs(leftDistance - rightDistance) / MAX_DIFF)) * speed;

			// Right Side Closer
			if (leftDistance > rightDistance) {
				rightSpeed = slowSide;
			}

			// Left Side Closer
			if (rightDistance > leftDistance) {
				leftSpeed = slowSide;
			}
		}

		rightSide.set(ControlMode.PercentOutput, rightSpeed, TIMEOUT);
		leftSide.set(ControlMode.PercentOutput, -leftSpeed, TIMEOUT);
	}

	public boolean haveCube() {
		return (getLeftDistance() < MIN_DISTANCE) && (getRightDistance() < MIN_DISTANCE);
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
		SmartDashboard.putNumber("Intake/Left/Sensor Distance", leftDistanceSensor.getDistance());
		SmartDashboard.putNumber("Intake/Left/Sensor Voltage", leftDistanceSensor.getAverageVoltage());
		SmartDashboard.putNumber("Intake/Right/Sensor Distance", rightDistanceSensor.getDistance());
		SmartDashboard.putNumber("Intake/Right/Sensor Voltage", rightDistanceSensor.getAverageVoltage());

		SmartDashboard.putNumber("Intake/Left/Motor Current", leftSide.getOutputCurrent());
		SmartDashboard.putNumber("Intake/Left/Motor Voltage", leftSide.getMotorOutputVoltage());
		SmartDashboard.putNumber("Intake/Right/Motor Current", rightSide.getOutputCurrent());
		SmartDashboard.putNumber("Intake/Right/Motor Voltage", rightSide.getMotorOutputVoltage());

		// SmartDashboard.putBoolean("Intake/Cradle Up?", intakeUpDown.get() ==
		// Value.kForward);

		SmartDashboard.putBoolean("Intake/Have Cube?", haveCube());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new IntakeControl());
	}

}
