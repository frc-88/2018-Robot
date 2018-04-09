package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmDownPart_1_v2 extends Command {
	private static final int STATE_DOWN = 10;
	private static final int STATE_PUSH = 20;
	private static final int STATE_ZERO = 30;
	private static final int STATE_END = 40;
	
	private static final double ZEROING_SPEED = -0.5;
	private static final double CURRENT_SPIKE = 10;
	
	private int state;
	
    public ArmDownPart_1_v2() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = STATE_DOWN;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	switch (state) {
    	case STATE_DOWN:
        	if(Robot.arm.isSafe()){
        		Robot.lift.setPosition(Lift.POS_ALMOST_BOTTOM, true);
        		Robot.lift.gotoPosition();
        	}
        	Robot.arm.setPositionToDown();
        	Robot.arm.gotoPosition();
        	if (Robot.arm.isDown()) {
        		state = STATE_PUSH;
        	}
        	break;
    	case STATE_PUSH:
    		Robot.arm.basicMotion(ZEROING_SPEED);
    		
    		if (Robot.arm.getCurrent() > CURRENT_SPIKE) {
    			state = STATE_ZERO;
    		}
    		
    		break;
    	case STATE_ZERO:
    		Robot.arm.zero();
    		Robot.arm.setPositionToDown();
    		state = STATE_END;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state == STATE_END;
    }

    // Called once after isFinished returns true
    protected void end() {  		
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
