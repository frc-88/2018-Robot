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
 * </pre>
 */
public class Intake extends Subsystem {
	private static final double TIMEOUT = 0;
	private static final double MAXSPEED = .75;

	private TalonSRX leftSide, rightSide;
	private SharpIR leftDistanceSensor, rightDistanceSensor;

	//Pneumatics
	DoubleSolenoid  intakeUpDown;

	public Intake() {
		leftSide = new TalonSRX(RobotMap.intakeLeft);
		rightSide = new TalonSRX(RobotMap.intakeRight);

		// TODO set up current limiting

		leftDistanceSensor = new SharpIR(RobotMap.intakeLeftIR);
		rightDistanceSensor = new SharpIR(RobotMap.intakeRightIR);
	}

	public void intakeWheelSpeed(double speed) {
		rightSide.set(ControlMode.PercentOutput, speed * MAXSPEED, TIMEOUT);
		leftSide.set(ControlMode.PercentOutput, speed * MAXSPEED, TIMEOUT);
	}

	public double getLeftDistance() {
		return leftDistanceSensor.getDistance();
	}

	public double getRightDistance() {
		return rightDistanceSensor.getDistance();
	}

	private void intakePneumatics(){

		intakeUpDown = new DoubleSolenoid(RobotMap.intakeSolenoidIn, RobotMap.intakeSolenoidOut);
		intakeUpDown.set(Value.kOff);


	}
	public void intakeCradleUp(){
		intakeUpDown.set(Value.kForward);

	}

	public void intakeCradleDown(){
		intakeUpDown.set(Value.kReverse);

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
		
		SmartDashboard.putBoolean("Intake/CradleUp", intakeUpDown.get()== Value.kForward);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new IntakeControl());
	}

}
