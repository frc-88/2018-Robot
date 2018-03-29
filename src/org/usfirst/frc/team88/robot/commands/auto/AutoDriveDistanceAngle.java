package org.usfirst.frc.team88.robot.commands.auto;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveDistanceAngle extends Command {

	// performance constants
	// TODO roll cruising speed and acceleration into constructor
	private static final double CRUISING_SPEED = 0.5;
	private static final double ACCELERATION = 0.01;
	private static final double COUNTS_PER_INCH = 1086;

	// states
	private static final int PREP = 10;
	private static final int ACCELERATE = 20;
	private static final int CRUISE = 30;
	private static final int DECELERATE = 40;
	private static final int STOP = 50;
	private static final int END = 60;
	
	private double targetDistance;
	private double targetHeading;
	private String targetDistancePref;
	private String targetAnglePref;
	
	private int state;
	private double speed;
	private double direction;
	private double accelerateDistance;

	public AutoDriveDistanceAngle(String distancePref, String anglePref) {
		requires(Robot.drive);

		targetDistancePref = distancePref;
		targetAnglePref = anglePref;
	}
	
	public AutoDriveDistanceAngle(String distancePref, double angle) {
		requires(Robot.drive);

		targetDistancePref = distancePref;
		targetHeading = angle;
	}
	
	public AutoDriveDistanceAngle(double distance, double angle) {
		requires(Robot.drive);

		targetDistance = Math.abs(distance * COUNTS_PER_INCH);
		direction = Math.signum(distance);
		targetHeading = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Preferences prefs = Preferences.getInstance();
		
		if (targetDistancePref != null) {
			targetDistance = Math.abs(prefs.getDouble(targetDistancePref, 0.0) * COUNTS_PER_INCH);
			System.out.println(targetDistancePref);
			System.out.println(targetDistance);
			direction = Math.signum(prefs.getDouble(targetDistancePref, 0.0));
		}

		if (targetAnglePref != null) {
			targetHeading = prefs.getDouble(targetAnglePref, 0.0);
			System.out.println(targetAnglePref);
			System.out.println(targetHeading);
		}
		state = PREP;
		speed = 0.0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double curve = (targetHeading - (Robot.drive.getYaw())) * 0.02;

		switch (state){
		case PREP:
			Robot.drive.resetEncoders();
			if(Math.abs(Robot.drive.getAvgPosition())<100){
				state = ACCELERATE;
			}
			break;
			
		case ACCELERATE:
			speed = speed + ACCELERATION;
			if(Math.abs(Robot.drive.getAvgPosition())> 3*targetDistance/7){
				state = DECELERATE;	
				accelerateDistance = Math.abs(Robot.drive.getAvgPosition()); 
			}
			else if (speed > CRUISING_SPEED) {
				state = CRUISE;
				accelerateDistance = Math.abs(Robot.drive.getAvgPosition()); 
			}
			break;
			
		case CRUISE:
			if (Math.abs(Robot.drive.getAvgPosition()) > (targetDistance - (accelerateDistance * 1.5))) {
				state = DECELERATE;
			}
			break;
			
		case DECELERATE:
			speed = speed - ACCELERATION;
			if (speed < 0) {
				speed = 0.0;
				state = STOP;
			}

			if (Math.abs(Robot.drive.getAvgPosition()) > targetDistance) {
				speed = 0;
				state = STOP;
			}

			break;
			
		case STOP:
			speed = 0.0;
			state = END;

			break;
		}

		if(state != PREP){
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
