package org.usfirst.frc.team88.robot.commands.intake;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeIntakeCube extends Command {

	int count;
	boolean isDone;
	int counts;
	int hasCubeCount;

	public IntakeIntakeCube(int s) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.intake);
		counts = s * 50;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		count = 0;
		hasCubeCount = 0;
		isDone = false;
		Robot.intake.cradleDown();
		Robot.intake.wheelSpeed(-1.0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		count++;
		if(Robot.intake.haveCube() && hasCubeCount++ > 10){
			isDone = true;
		}
		if (count > counts) {
			isDone = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isDone;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intake.wheelSpeed(-0.15);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.intake.wheelSpeed(0.0);
	}
}