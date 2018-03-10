package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.util.TJUtility;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftMove extends Command {
	private static final double DEADZONE = 0.1;
	private static final int MAX_MOVE = 40;
	
	private boolean stickControl;
	
    public LiftMove() {
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	stickControl = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int move = (int) TJUtility.deadZone(Robot.oi.operator.getLeftStickY(), DEADZONE);
    	
    	if (move != 0) {
    		stickControl = true;
    		Robot.lift.basicMotion(move);
    	} else if (stickControl) {
    		Robot.lift.setPosition(Robot.lift.getPosition());
    		stickControl = false;
    	} else {
    	  	Robot.lift.gotoPosition();
    	}
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
    }
}
