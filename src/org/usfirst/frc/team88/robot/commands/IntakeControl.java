package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.util.TJUtility;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeControl extends Command {
	private static final double DEADZONE = 0.1;
	private static final double POLY_A = 0.6;
	private static final double POLY_B = 0.1;
	private static final double POLY_C = 0.3;

	public IntakeControl() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double input;
		double fast = TJUtility.polynomial(Robot.oi.operator.getLeftTrigger(), POLY_A, POLY_B, POLY_C, DEADZONE);
		double slow = TJUtility.polynomial(Robot.oi.operator.getRightTrigger(), POLY_A, POLY_B, POLY_C, DEADZONE);
		double intake = TJUtility.polynomial(Math.sqrt(Math.pow(Robot.oi.operator.getRightStickX(), 2) + Math.pow(Robot.oi.operator.getRightStickY(), 2)), POLY_A, POLY_B, POLY_C, DEADZONE);

		if (slow != 0) {
			input = slow * 0.5;
		} else if (fast != 0) {
			input = fast;
		} else {
			input = -intake;
		}
		
		if (input != 0) {
			if (Robot.intake.haveCube()) {
				Robot.oi.operator.rumble(1);
			} else {
				Robot.oi.operator.rumble(0);
			}
		}
		else {
			Robot.oi.operator.rumble(0);
		}

		if (input == 0 && Robot.lift.getPercentHeight() > 0.1){
			input = -0.15; 
		}
		
		Robot.intake.wheelSpeed(input);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.intake.wheelSpeed(0.0);
	}
}
