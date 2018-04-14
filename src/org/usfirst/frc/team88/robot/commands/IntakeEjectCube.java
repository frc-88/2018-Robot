package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeEjectCube extends Command {
	
	int count;
	boolean isDone;
	int targetPosition;
	double speed;
    public IntakeEjectCube(int tp) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    	targetPosition = tp;
    	speed = 1;
    }
    public IntakeEjectCube(int tp, double s) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    	targetPosition = tp;
    	speed = s;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    	isDone = false;
    	
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
		
		
		
		if(Robot.lift.onTarget(targetPosition)){
			Robot.intake.wheelSpeed(speed);
			
			count++;
			
			if (count > 20) {
				Robot.intake.wheelSpeed(0.0);
				isDone = true;
			} 
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
