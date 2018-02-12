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
	private static final double TIMEOUT = 0;
	private static final double MAXSPEED = .75;
	private TalonSRX leftSide, rightSide;
	private SharpIR leftDistanceSensor, rightDistanceSensor;
	// pneumatics
	DoubleSolenoid upDown;

	public Intake() {
		leftSide = new TalonSRX(RobotMap.intakeLeft);
		rightSide = new TalonSRX(RobotMap.intakeRight);

		leftDistanceSensor = new SharpIR(RobotMap.intakeLeftIR);
		rightDistanceSensor = new SharpIR(RobotMap.intakeRightIR);

//		upDown = new DoubleSolenoid(RobotMap.intakeSolenoidIn, RobotMap.intakeSolenoidOut);
//		upDown.set(Value.kOff);
	}

	// Sets intake wheel speed
	public void intakeWheelSpeed(double speed) {
		rightSide.set(ControlMode.PercentOutput, speed * MAXSPEED, TIMEOUT);
		leftSide.set(ControlMode.PercentOutput, -speed * MAXSPEED, TIMEOUT);
	}

	// Gets the cube distance from left sensor
	public double getLeftDistance() {
		return leftDistanceSensor.getDistance();
	}

	// Gets the cube distance from right sensor
	public double getRightDistance() {
		return rightDistanceSensor.getDistance();
	}

	// Pneumaticly puts cradle up
	public void intakeCradleUp() {
		upDown.set(Value.kForward);

	}

	// Pneumaticly puts cradle down
	public void intakeCradleDown() {
		upDown.set(Value.kReverse);

	}

	// Pulls the cube in if Sideways
	public void cubePullIn(double maxSpeed) {

		double rightPullSpeed = maxSpeed;
		double leftPullSpeed = maxSpeed;
		final double MAX_DIFF = 5;
		double leftDistance = leftDistanceSensor.getDistance();
		double rightDistance = rightDistanceSensor.getDistance();
		double difference = leftDistance - rightDistance;
		double adjustment = 1 - (0.50 * Math.abs(difference) / MAX_DIFF);
		double slowSide = adjustment * maxSpeed;

		// Right Side Closer
		if (leftDistance > rightDistance) {
			rightPullSpeed = slowSide;
		}

		// Left Side Closer
		if (rightDistance > leftDistance) {
			leftPullSpeed = slowSide;
		}

		rightSide.set(ControlMode.PercentOutput, rightPullSpeed, TIMEOUT);
		leftSide.set(ControlMode.PercentOutput, leftPullSpeed, TIMEOUT);

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

//		 SmartDashboard.putBoolean("Intake/CradleUp", intakeUpDown.get() == Value.kForward);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new IntakeControl());
	}

}
