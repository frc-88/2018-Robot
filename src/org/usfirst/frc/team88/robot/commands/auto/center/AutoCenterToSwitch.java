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
	private static final double CRUISING_SPEED = 0.6;
	private static final double ACCELERATION = 0.01;
	private static final double COUNTS_PER_INCH = 1086;
	private static final double STAGE_ONE = 10;
	
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
	private int count;
	private double curve;
	
	private boolean shooting;
	private boolean scored;
	

	public AutoCenterToSwitch() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
		requires(Robot.lift);
		requires(Robot.intake);
		requires(Robot.arm);
		requires(Robot.dingleball);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("TJ_AUTO:AutoCenterToSwitch:initialize");
		state = PREP;
		cubeUp=false;
		shooting = false;
		scored = false;
		done = false;
		speed = 0.0;
		count = 0;
		curve = 0.0;
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
		targetDistanceCounts = (STAGE_ONE + stageTwoDistanceInches + stageThreeDistance) * COUNTS_PER_INCH;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//double curve;
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
				Robot.arm.setPositionToDown();
				Robot.arm.gotoPosition();
				Robot.dingleball.ballsFoward();
				cubeUp = true;
			}
		} else {
			curve = 0.0;
		}

		switch (state) {
		case PREP:
			Robot.drive.resetEncoders();
			Robot.drive.resetDisplacement();
			
			if (Math.abs(Robot.drive.getAvgPosition()) < 100) {
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
			
			// if we get here and we haven't started shooting yet, start shooting
			if (!scored && !shooting) {
				Robot.intake.wheelSpeed(0.75);
				shooting = true;
			}
			
			else if (scored) {
				state = END;
			}
			
			break;
		case END:
			done = true;
			break;
		}
		
//		double jerkX = Math.abs(Robot.drive.getJerkX());
//		if(!scored && !shooting && (avgPosition > (STAGE_ONE + stageTwoDistanceInches) * COUNTS_PER_INCH) && jerkX > .6){
//			Robot.intake.wheelSpeed(0.75);
//			shooting = true;
//		}

		if(shooting){
			count++;
			if(count > 20){
				scored = true;
				shooting = false;
				Robot.intake.wheelSpeed(0.0);
				state = STOP;
			}
		}
		
		if (state != PREP) {
			Robot.drive.driveCurve(speed, curve);
		}
		updateLog();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return done;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("TJ_AUTO:AutoCenterToSwitch:end");
		Robot.drive.wheelSpeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("TJ_AUTO:AutoCenterToSwitch:interrupted");
		Robot.drive.wheelSpeed(0, 0);
		Robot.intake.wheelSpeed(0.0);
	}
	
	public void updateLog(){
		System.out.format("TJ_AUTO:%d,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%b,%b,%b",
				state,
				Robot.drive.getAvgPosition(), 
				Robot.drive.getYaw(), 
				targetYaw,
				Robot.drive.getJerkX(),
				speed,
				curve,
				cubeUp,		   
				shooting,
				scored);
	}
}
