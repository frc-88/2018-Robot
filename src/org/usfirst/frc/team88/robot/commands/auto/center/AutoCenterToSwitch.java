package org.usfirst.frc.team88.robot.commands.auto.center;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCenterToSwitch extends Command {
	// states
	private static final int PREP = 0;
	private static final int ACCELERATE = 10;
	private static final int CRUISE = 1;
	private static final int DECELERATE = 5;
	private static final int STOP = 2;
	private static final int END = 3;
	private static final double CRUISING_SPEED = 0.5;
	private static final double ACCELERATION = 0.01;
	private static final double COUNTS_PER_INCH = 1086;
	private static final double STAGE_ONE = 10;

	private int state;
	private double speed;
	private double stageOneCounts;
	private double stageTwoCounts;
	private double targetDistanceCounts;
	private double targetYaw;
	private double accelerateDistance;
	private double stageTwoYaw;
	private double stageTwoDistanceInches;
	private double stageThreeYaw;
	private double stageThreeDistance;
	private boolean cubeUp;
	private boolean shooting;
	private boolean scored;
	private boolean done;
	private String gameData;
	private int count;

	public AutoCenterToSwitch() {
		requires(Robot.drive);
		requires(Robot.lift);
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		state = PREP;
		cubeUp = false;
		shooting = false;
		scored = false;
		done = false;
		speed = 0.0;
		count = 0;

		Robot.drive.zeroYaw();
		Robot.drive.resetEncoders();

		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L') {
			stageTwoYaw = -90;
			stageTwoDistanceInches = 45;
			stageThreeYaw = 20;
			stageThreeDistance = 95;
		} else if (gameData.charAt(0) == 'R') {
			stageTwoYaw = 75;
			stageTwoDistanceInches = 50;
			stageThreeYaw = -20;
			stageThreeDistance = 80;
		}

		stageOneCounts = STAGE_ONE * COUNTS_PER_INCH;
		stageTwoCounts = (STAGE_ONE + stageTwoDistanceInches) * COUNTS_PER_INCH;
		targetDistanceCounts = (STAGE_ONE + stageTwoDistanceInches + stageThreeDistance) * COUNTS_PER_INCH;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double curve;
		double avgPosition = Math.abs(Robot.drive.getAvgPosition());

		// state machine controls our speed in a trapezoidal profile
		switch (state) {
		case PREP:
			if (avgPosition < 100) {
				Robot.intake.cradleDown();
				state = ACCELERATE;
			} else {
				Robot.drive.resetEncoders();
			}
			break;

		case ACCELERATE:
			speed = speed + ACCELERATION;
			if (avgPosition > targetDistanceCounts) {
				state = DECELERATE;
				accelerateDistance = avgPosition;
			} else if (speed > CRUISING_SPEED) {
				state = CRUISE;
				accelerateDistance = avgPosition;
			}
			break;

		case CRUISE:
			if (avgPosition > (targetDistanceCounts - (accelerateDistance * 1.4))) {
				state = DECELERATE;
			}
			break;

		case DECELERATE:
			speed = speed - ACCELERATION;
			if (speed < 0) {
				speed = 0.0;
				state = STOP;
			}

			if (avgPosition > targetDistanceCounts) {
				speed = 0;
				state = STOP;
			}
			break;

		case STOP:
			speed = 0.0;
			state = END;
			break;

		case END:
			done = true;
			break;
		}

		// if we are not in PREP state (that is, encoders have been reset)
		// then adjust our targetHeading based on how far we have driven
		if (state != PREP) {
			if (avgPosition < stageOneCounts) {
				targetYaw = 0.0;
			} else if (avgPosition < stageTwoCounts) {
				targetYaw = stageTwoYaw;
			} else {
				targetYaw = stageThreeYaw;
			}

			curve = (targetYaw - (Robot.drive.getYaw())) * 0.01;

			// once we have driven a little bit, raise the lift to get ready to shoot
			if (avgPosition > stageOneCounts && !cubeUp) {
				Robot.lift.setPosition(Lift.POS_SWITCH);
				Robot.lift.gotoPosition();
				cubeUp = true;
			}
		} else {
			curve = 0.0;
		}

		// if the lift is up, we haven't shot, and we have driven far enough
		// shoot when we collide with the fence
		if (cubeUp && !scored && !shooting && (avgPosition > stageTwoCounts)
				&& (Math.abs(Robot.drive.getJerkX()) > .6)) {
			Robot.intake.wheelSpeed(0.75);
			shooting = true;
		}

		// run the intake for 20*20 = 400ms, then we're done!
		if (shooting) {
			count++;
			if (count > 20) {
				scored = true;
				shooting = false;
				Robot.intake.wheelSpeed(0.0);
				state = STOP;
			}
		}

		// don't move until we've completed PREP state
		// that is, don't move until the encoders are reset
		if (state != PREP) {
			Robot.drive.driveCurve(speed, curve);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return done;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drive.wheelSpeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drive.wheelSpeed(0, 0);
		Robot.intake.wheelSpeed(0.0);
	}
}
