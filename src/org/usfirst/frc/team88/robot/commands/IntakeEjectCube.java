package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeEjectCube extends Command {
	
	int count;
	boolean isDone;
	
    public IntakeEjectCube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    	isDone = false;
    	Robot.intake.wheelSpeed(1.0);
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
		
		count++;
		
		if (count > 20) {
			Robot.intake.wheelSpeed(0.0);
			Robot.intake.cradleDown();
			isDone = true;
		} 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
