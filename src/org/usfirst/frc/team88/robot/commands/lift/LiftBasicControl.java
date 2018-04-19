package org.usfirst.frc.team88.robot.commands.lift;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.util.TJUtility;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftBasicControl extends Command {
	private static final double DEADZONE = 0.1;
	private static final double POLY_A = 0.35;
	private static final double POLY_B = 0.5;
	private static final double POLY_C = 0.15;

    public LiftBasicControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.basicMotion(TJUtility.polynomial(Robot.oi.operator.getLeftStickY(), POLY_A, POLY_B, POLY_C, DEADZONE));
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
