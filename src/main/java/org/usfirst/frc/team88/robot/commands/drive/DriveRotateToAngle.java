package org.usfirst.frc.team88.robot.commands.drive;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveRotateToAngle extends Command {
	double targetAngle;
	String targetPref;
	
	
	private Preferences prefs = Preferences.getInstance();
	
    public DriveRotateToAngle(double angle) {
        requires(Robot.drive);

        targetAngle = angle;
    }
    
    public DriveRotateToAngle(String pref){
    	requires(Robot.drive);
    	
    	targetPref = pref;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		if (targetPref != null) {
			targetAngle = prefs.getDouble(targetPref, 0.0);
		}
		
		Robot.drive.rotateController.reset();
    	Robot.drive.rotateController.setSetpoint(targetAngle);
    	Robot.drive.rotateController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		SmartDashboard.putNumber("Rot Error", Robot.drive.rotateController.getError());
		SmartDashboard.putNumber("Rot On Target", Robot.drive.rotateController.onTarget()?1:0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return Robot.drive.rotateController.onTarget();
	}

    // Called once after isFinished returns true
    protected void end() {
		Robot.drive.rotateController.disable();
		Robot.drive.wheelSpeed(0, 0);
   }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.drive.rotateController.disable();    	
		Robot.drive.wheelSpeed(0, 0);
   }
   
}
