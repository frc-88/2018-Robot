package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.RobotMap;
import org.usfirst.frc.team88.robot.commands.IntakeCommand;
import org.usfirst.frc.team88.robot.util.SharpIR;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Kyle Hackett
 * 
 * Intake Haiku v2.
 * 
 * The green wheels spin fast
 * The cube is now ours to use
 * And now we will win
 *
 */
public class Intake extends Subsystem {
	final double MAXSPEED = .75;
	final double LOWERSPEED = .50;
	private Talon leftSide, rightSide; 
	private SharpIR leftDistanceSensor, rightDistanceSensor;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Intake () {
		leftSide = new Talon(RobotMap.intakeLeftTalon);
		rightSide = new Talon(RobotMap.intakeRightTalon);
		
		leftDistanceSensor = new SharpIR(RobotMap.intakeLeftIR);
		rightDistanceSensor = new SharpIR(RobotMap.intakeRightIR);
	}

	public void intakeWheelSpeed (double speed) {

		if (Robot.oi.driver.getZ() > 0){
			rightSide.set(speed * LOWERSPEED);
			leftSide.set(speed * MAXSPEED);
		} else { 
			rightSide.set(speed * MAXSPEED);
			leftSide.set(speed * MAXSPEED);
		}

	}

	public double getLeftDistance(){
		return leftDistanceSensor.getDistance();
	}

	public double getRightDistance(){
		return rightDistanceSensor.getDistance();
	}

	public void updateDashboard() {
		SmartDashboard.putNumber("Intake/Left Distance", leftDistanceSensor.getDistance());
		SmartDashboard.putNumber("Intake/Right Distance", rightDistanceSensor.getDistance());		
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new IntakeCommand());
	}



}

