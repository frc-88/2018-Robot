package org.usfirst.frc.team88.robot.commands.auto;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveDistanceTurn extends Command {

	// performance constants
	// TODO roll cruising speed and acceleration into constructor
	private static final double CRUISING_SPEED = 1;
	private static final double ACCELERATION = 0.03;
	private static final double COUNTS_PER_INCH = 1086;

	// states
	private static final int PREP = 10;
	private static final int ACCELERATE = 20;
	private static final int CRUISE = 30;
	private static final int DECELERATE = 40;
	private static final int STOP = 50;
	private static final int END = 60;

	private double targetDistanceLeg1;
	private String targetDistanceLeg1Pref;
	private double targetDistanceLeg2;
	private String targetDistanceLeg2Pref;
	private double targetHeading;
	private boolean isRightTurn;

	private int state;
	private double speed;
	private double direction;
	private double accelerateDistance;

	public AutoDriveDistanceTurn(String distanceLeg1Pref, String distanceLeg2Pref, boolean rightTurn) {
		requires(Robot.drive);

		targetDistanceLeg1Pref = distanceLeg1Pref;
		targetDistanceLeg2Pref = distanceLeg2Pref;
		isRightTurn = rightTurn;
	}

	public AutoDriveDistanceTurn(double distanceLeg1, double distanceLeg2, boolean rightTurn) {
		requires(Robot.drive);

		targetDistanceLeg1 = Math.abs(distanceLeg1 * COUNTS_PER_INCH);
		targetDistanceLeg2 = Math.abs(distanceLeg2 * COUNTS_PER_INCH);
		direction = Math.signum(distanceLeg1);
		isRightTurn = rightTurn;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Preferences prefs = Preferences.getInstance();

		if (targetDistanceLeg1Pref != null) {
			targetDistanceLeg1 = Math.abs(prefs.getDouble(targetDistanceLeg1Pref, 0.0) * COUNTS_PER_INCH);
			direction = Math.signum(prefs.getDouble(targetDistanceLeg1Pref, 0.0));
		}

		if (targetDistanceLeg2Pref != null) {
			targetDistanceLeg2 = Math.abs(prefs.getDouble(targetDistanceLeg2Pref, 0.0) * COUNTS_PER_INCH);
		}

		state = PREP;
		speed = 0.0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double curve;
		double avgPosition = Math.abs(Robot.drive.getAvgPosition());

		if (state != PREP) {
			if (avgPosition < targetDistanceLeg1) {
				targetHeading = 0.0;
			} else {
				targetHeading = (isRightTurn ? 95 : -95);
			}

			curve = (targetHeading - (Robot.drive.getYaw())) * 0.01;
		} else {
			curve = 0.0;
		}

		switch (state) {
		case PREP:
			Robot.drive.resetEncoders();
			if (Math.abs(Robot.drive.getAvgPosition()) < 100) {
				state = ACCELERATE;
			}
			break;

		case ACCELERATE:
			speed = speed + ACCELERATION;
			if (Math.abs(Robot.drive.getAvgPosition()) > 3 * targetDistanceLeg2 / 7) {
				state = DECELERATE;
				accelerateDistance = Math.abs(Robot.drive.getAvgPosition());
				SmartDashboard.putNumber("accelerateDistance", accelerateDistance);
			} else if (speed > CRUISING_SPEED) {
				state = CRUISE;
				accelerateDistance = Math.abs(Robot.drive.getAvgPosition());
				SmartDashboard.putNumber("accelerateDistance", accelerateDistance);
			}
			break;

		case CRUISE:
			if (Math.abs(Robot.drive.getAvgPosition()) > (targetDistanceLeg2 - (accelerateDistance * 1.5))) {
				state = DECELERATE;
			}
			break;

		case DECELERATE:
			speed = speed - ACCELERATION;
			if (speed < 0) {
				speed = 0.0;
				state = STOP;
			}

			if (Math.abs(Robot.drive.getAvgPosition()) > targetDistanceLeg2) {
				speed = 0;
				state = STOP;
			}

			break;

		case STOP:
			speed = 0.0;
			state = END;

			break;
		}

		if (state != PREP) {
			Robot.drive.driveCurve(speed * direction, curve);
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
