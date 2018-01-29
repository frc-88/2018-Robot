package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.RobotMap;
import org.usfirst.frc.team88.robot.commands.IntakeCommand;
import org.usfirst.frc.team88.robot.util.SharpIR;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;
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
 * </pre>
 */
public class Intake extends Subsystem {
	private static final double TIMEOUT = 0;
	private static final double MAXSPEED = .75;
	private static final double LOWERSPEED = .50;

	private TalonSRX leftSide, rightSide;
	private SharpIR leftDistanceSensor, rightDistanceSensor;

	public Intake() {
		leftSide = new TalonSRX(RobotMap.intakeLeft);
		rightSide = new TalonSRX(RobotMap.intakeRight);

		leftDistanceSensor = new SharpIR(RobotMap.intakeLeftIR);
		rightDistanceSensor = new SharpIR(RobotMap.intakeRightIR);
	}

	public void intakeWheelSpeed(double speed) {

		if (Robot.oi.driver.getZ() > 0) {
			rightSide.set(ControlMode.PercentOutput, speed * LOWERSPEED, TIMEOUT);
			leftSide.set(ControlMode.PercentOutput, speed * MAXSPEED, TIMEOUT);
		} else {
			rightSide.set(ControlMode.PercentOutput, speed * MAXSPEED, TIMEOUT);
			leftSide.set(ControlMode.PercentOutput, speed * MAXSPEED, TIMEOUT);
		}

	}

	public double getLeftDistance() {
		return leftDistanceSensor.getDistance();
	}

	public double getRightDistance() {
		return rightDistanceSensor.getDistance();
	}

	public void updateDashboard() {
		SmartDashboard.putNumber("Intake/Left Distance", leftDistanceSensor.getDistance());
		SmartDashboard.putNumber("Intake/Right Distance", rightDistanceSensor.getDistance());

		SmartDashboard.putNumber("Intake/Left Current", leftSide.getOutputCurrent());
		SmartDashboard.putNumber("Intake/Left Voltage", leftSide.getMotorOutputVoltage());
		SmartDashboard.putNumber("Intake/Right Current", rightSide.getOutputCurrent());
		SmartDashboard.putNumber("Intake/Right Voltage", rightSide.getMotorOutputVoltage());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new IntakeCommand());
	}

}
