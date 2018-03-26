package org.usfirst.frc.team88.robot.commands.auto.center;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	private static final double targetDisplacementX = 0; //Make sure these three are correct
	private static final double toleranceX = 0;
	private static final double toleranceY = 0;
	// private static final double STAGE_THREE = 75;

	private int state;
	private double speed;
	private double targetDistanceCounts;
	private double targetYaw;
	private double accelerateDistance;
	private double stageTwoYaw;
	private double stageTwoDistanceInches;
	private double stageThreeYaw;
	private double stageThreeDistance;
	private boolean cubeUp;
	private boolean done;
	private String gameData;
	private double count;
	
	private double targetDisplacementY = 0;
	private boolean shooting = false;
	private boolean scored = false;
	

	public AutoCenterToSwitch() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
		requires(Robot.lift);
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		state = PREP;
		cubeUp=false;
		done = false;
		speed = 0.0;
		count = 0.0;

		Robot.drive.zeroYaw();
		Robot.drive.resetEncoders();

		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L') {
			stageTwoYaw = -90;
			stageTwoDistanceInches = 45;
			stageThreeYaw = 20;
			stageThreeDistance = 95;
			targetDisplacementY = 0; //make sure this right
		} else if (gameData.charAt(0) == 'R') {
			stageTwoYaw = 75;
			stageTwoDistanceInches = 50;
			stageThreeYaw = -20;
			stageThreeDistance = 60;
			targetDisplacementY = 0; //make sure this right
		}
		targetDistanceCounts = (STAGE_ONE + stageTwoDistanceInches + stageThreeDistance) * COUNTS_PER_INCH;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double curve;
		double avgPosition = Robot.drive.getAvgPosition();

		if (state != PREP) {
			if (avgPosition < STAGE_ONE * COUNTS_PER_INCH) {
				targetYaw = 0.0;
			} else if (avgPosition < (STAGE_ONE + stageTwoDistanceInches) * COUNTS_PER_INCH) {
				targetYaw = stageTwoYaw;
			} else {
				targetYaw = stageThreeYaw;
			}

			curve = (targetYaw - (Robot.drive.getYaw())) * 0.01;

			if (avgPosition > STAGE_ONE * COUNTS_PER_INCH && !cubeUp) {
				Robot.lift.setPosition(Lift.POS_SWITCH);
				Robot.lift.gotoPosition();
				cubeUp = true;
			}
		} else {
			curve = 0.0;
		}

		switch (state) {
		case PREP:
			Robot.drive.resetEncoders();
			
			if (Math.abs(Robot.drive.getAvgPosition()) < 100) {
				Robot.intake.cradleDown();
				state = ACCELERATE;
			}
			break;
		case ACCELERATE:
			speed = speed + ACCELERATION;
			if (Robot.drive.getAvgPosition() > targetDistanceCounts) {
				state = DECELERATE;
				accelerateDistance = Robot.drive.getAvgPosition();
				SmartDashboard.putNumber("accelerateDistance", accelerateDistance);
			} else if (speed > CRUISING_SPEED) {
				state = CRUISE;
				accelerateDistance = Robot.drive.getAvgPosition();
				SmartDashboard.putNumber("accelerateDistance", accelerateDistance);
			}
			break;
		case CRUISE:
			if (Robot.drive.getAvgPosition() > (targetDistanceCounts - (accelerateDistance * 1.4))) {
				state = DECELERATE;
			}
			break;
		case DECELERATE:
			speed = speed - ACCELERATION;
			if (speed < 0) {
				speed = 0.0;
				state = STOP;
			}

			if (Robot.drive.getAvgPosition() > targetDistanceCounts) {
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
		
		if(!scored && !shooting && Math.abs(Robot.drive.getJerkX()) > 20 && 
				Math.abs(targetDisplacementX - Robot.drive.getDisplacementX()) > toleranceX && 
				Math.abs(targetDisplacementY - Robot.drive.getDisplacementY()) > toleranceY){
			Robot.intake.wheelSpeed(0.75);
			shooting = true;
		}
		if(shooting = true){
			count++;
			if(count > 20){
				scored = true;
				shooting = false;
				Robot.intake.wheelSpeed(0.0);
			}
		}
		if (state != PREP) {
			Robot.drive.driveCurve(speed, curve);
		}
		Robot.drive.updateDashboard();
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
