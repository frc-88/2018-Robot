package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftCalibrate extends Command {
	private static final int PREP = 10;
	private static final int UP = 20;
	private static final int PAUSE = 30;
	private static final int DOWN = 40;
	private static final int BOTTOM = 50;
	private static final int END = 60;
	
	private static final double SPEED = 0.3;
	private static final double COUNTS = 20;
	private static final double CURRENT_THRESHOLD = 5;
	
	private int state;
	private int count;
	
    public LiftCalibrate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = PREP;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (state) {
    	case PREP: 
    		count = 0;
    		Robot.lift.disableSoftLimits();
    		state = UP;
    		break;
    	case UP:
    		Robot.lift.basicMotion(SPEED);
    		if (count++ > COUNTS) {
    			count = 0;
    			state = PAUSE;
    		}
    		break;
    	case PAUSE:
    		Robot.lift.basicMotion(0.0);
    		if (count++ > COUNTS) {
    			count = 0;
    			state = DOWN;
    		}
    	case DOWN:
    		Robot.lift.basicMotion(-SPEED);
    		if (count++ > (COUNTS*2)) {
    			state = END;
    		}
    		
    		if (Robot.lift.getMasterCurrent() > CURRENT_THRESHOLD) {
    			Robot.lift.basicMotion(0.0);
    			state = BOTTOM;
    		}
    		break;
    	case BOTTOM:
    		Robot.lift.setReverseLimit();
    		state = END;
    		break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state == END;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lift.basicMotion(0.0);
    }
}
