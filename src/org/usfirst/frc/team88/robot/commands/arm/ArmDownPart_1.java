package org.usfirst.frc.team88.robot.commands.arm;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmDownPart_1 extends Command {

    public ArmDownPart_1() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.arm.isSafe()){
    		Robot.lift.setPosition(Lift.POS_ALMOST_BOTTOM, true);
    		Robot.lift.gotoPosition();
    	}
    	Robot.arm.setPositionToDown();
    	Robot.arm.gotoPosition();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.arm.isDown();
    }

    // Called once after isFinished returns true
    protected void end() {  		
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
