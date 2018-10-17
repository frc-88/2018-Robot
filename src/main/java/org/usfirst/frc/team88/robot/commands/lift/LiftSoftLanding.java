package org.usfirst.frc.team88.robot.commands.lift;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftSoftLanding extends Command {

	private boolean done;
	private int count;
	private boolean dangerZone;
	
    public LiftSoftLanding() {
    	requires(Robot.lift);
    	dangerZone=false;
    }
    public LiftSoftLanding(boolean dangermode){
    	requires(Robot.lift);
    	dangerZone=dangermode;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
    	count = 0;
		Robot.lift.setPosition(Lift.POS_ALMOST_BOTTOM, dangerZone);
		Robot.lift.gotoPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.lift.belowTarget(Lift.POS_ALMOST_BOTTOM, dangerZone)) {
    		Robot.lift.basicMotion(-0.1);
    		count++;
    	}
    	
    	if (count > 1 && Robot.lift.onTarget(Lift.POS_BOTTOM, dangerZone)) {
    		done = true;
    	} else if (count > 60) {
    		done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.setPosition(Lift.POS_BOTTOM, dangerZone);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
