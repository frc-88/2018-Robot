package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DingleDance extends Command {
	
	private static int count = 0;
	private static int countd = 0;
    public DingleDance() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.dingleball);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    	countd = 0;
    	Robot.dingleball.dance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(countd == 200){
    		Robot.dingleball.dance();
    		countd=0;
    	}
    	count++;
    	countd++;
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count == 1200;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dingleball.ballsDown();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.dingleball.ballsDown();
    }
}
