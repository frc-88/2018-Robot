package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.util.TJUtility;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveSplitArcade extends Command {
	public final static double SENSITIVITY = 0.25;
	private static final double DEADZONE = 0.1;
	private static final double POLY_A = 0.35;
	private static final double POLY_B = 0.5;
	private static final double POLY_C = 0.15;
	
	public DriveSplitArcade() {
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double magnitude, curve, targetHeading, error;

		// below for rocket league style
		// magnitude = InputShaping.applyPoly(Robot.oi.driver.getZ());
		magnitude = TJUtility.polynomial(Robot.oi.driver.getLeftStickY(), POLY_A, POLY_B, POLY_C, DEADZONE);
		curve = TJUtility.polynomial(Robot.oi.driver.getRightStickX(), POLY_A, POLY_B, POLY_C, DEADZONE);

		if ((curve == 0) && (magnitude != 0)) {
			if (Robot.oi.driver.isButtonAPressed()) {
				targetHeading = 180;
			} else if (Robot.oi.driver.isButtonBPressed()) {
				targetHeading = 90;
			} else if (Robot.oi.driver.isButtonXPressed()) {
				targetHeading = -90;
			} else if (Robot.oi.driver.isButtonYPressed()) {
				targetHeading = 0;
			} else {
				targetHeading = 999;
			}

			if (targetHeading != 999) {
				error = TJUtility.normalizeAngle(targetHeading - Robot.drive.getYaw());
				curve = TJUtility.maxValue(error * 0.013, 1.0);
			}
		}

		Robot.drive.driveCurve(magnitude, curve, SENSITIVITY);
		Robot.drive.updateDashboard();
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
		Robot.drive.wheelSpeed(0, 0);
	}
}
