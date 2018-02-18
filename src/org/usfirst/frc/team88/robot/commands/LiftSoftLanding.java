package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftSoftLanding extends Command {

	private static final int OFFSET = 60;
	private boolean done;
	private int count;
	
    public LiftSoftLanding() {
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
    	count = 0;
		Robot.lift.setPosition(Lift.POS_BOTTOM + OFFSET);
		Robot.lift.gotoPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.lift.onTarget(Lift.POS_BOTTOM + OFFSET)) {
    		count++;
    	} else if ((count > 10 && Robot.lift.onTarget(Lift.POS_BOTTOM)) || count > 100) {
    		Robot.lift.setPosition(Lift.POS_BOTTOM);
    		done = true;
    	}

    	if (count > 10) {
    		count++;
    		Robot.lift.basicMotion(-0.2);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
