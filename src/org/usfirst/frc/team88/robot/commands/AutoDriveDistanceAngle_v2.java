package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.util.TJUtility;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveDistanceAngle_v2 extends Command {
	// TODO Allow Cruising speed and acceleration to be passed in as arguments
	private static final double CRUISING_SPEED = 0.5;
	private static final double ACCELERATION = 0.01;
	private static final double COUNTS_PER_INCH = 805;
	private final static double MAX_SPEED = 13000;
	
	// states
	private static final int PREP = 0;
	private static final int ACCELERATE = 10;
	private static final int CRUISE = 1;
	private static final int DECELERATE = 5;
	private static final int STOP = 2;
	private static final int END = 3;

	private final double targetDistance;
	private final double targetHeading;

	private int state;
	private double speed;

	public AutoDriveDistanceAngle_v2(double distance, double angle) {
		requires(Robot.drive);

		targetDistance = distance * COUNTS_PER_INCH;
		targetHeading = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drive.wheelSpeed(0, 0);
		Robot.drive.resetEncoders();
		state = PREP;
		speed = 0.0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double curve = TJUtility.maxValue((targetHeading - (Robot.drive.getYaw())) * 0.02, 0.5);
		double currentPosition = Robot.drive.getAvgPosition();

		switch (state) {
		case PREP:
			Robot.drive.resetEncoders();
			if (Math.abs(currentPosition) < 100) {
				state = ACCELERATE;
			}
			break;

		case ACCELERATE:
			speed = speed + ACCELERATION;
			if (speed > CRUISING_SPEED) {
				state = CRUISE;
			}
			// intentionally fall through to CRUISE. Both need to
			// determine how far it will take us to stop at current velocity

		case CRUISE:
			// velocity returned from encoders is in counts per 100ms
			// so, divide by 5 to get counts per 20ms (our cycle time)
			double velocity = Robot.drive.getAvgVelocity() / 5.0;
			double stopDistance = 0.0;

			while (velocity > 0) {
				stopDistance += velocity;
				velocity -= ACCELERATION * MAX_SPEED;
			}

			if (stopDistance > targetDistance - currentPosition) {
				state = DECELERATE;
			}

			break;

		case DECELERATE:
			speed = speed - ACCELERATION;
			if ((speed < 0) || (currentPosition > targetDistance)) {
				speed = 0.0;
				state = STOP;
			}

			break;

		case STOP:
			speed = 0.0;
			state = END;
			break;
		}

		if (state != PREP) {
			Robot.drive.driveCurve(speed, curve);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return state == END;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drive.wheelSpeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drive.wheelSpeed(0, 0);
	}
}
