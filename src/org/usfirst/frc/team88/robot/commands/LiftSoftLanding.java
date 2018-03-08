package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftSoftLanding extends Command {

	private boolean done;
	private int count;
	
    public LiftSoftLanding() {
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
    	count = 0;
		Robot.lift.setPosition(Lift.POS_ALMOST_BOTTOM);
		Robot.lift.gotoPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.lift.belowTarget(Lift.POS_ALMOST_BOTTOM)) {
    		Robot.lift.basicMotion(-0.1);
    		count++;
    	}
    	
    	if (count > 1 && Robot.lift.onTarget(Lift.POS_BOTTOM)) {
    		done = true;
    	} else if (count > 20) {
    		done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.setPosition(Lift.POS_BOTTOM);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
