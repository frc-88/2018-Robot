package org.usfirst.frc.team88.robot.commands.auto.center;

import org.usfirst.frc.team88.robot.commands.PowerUpConditionalCommand;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistance;
import org.usfirst.frc.team88.robot.commands.drive.DriveRotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter_Part3 extends CommandGroup {

	public AutoCenter_Part3() {
		// back off and turn towards our scale
		addSequential(new AutoDriveDistance(-24));
		addSequential(new PowerUpConditionalCommand(new DriveRotateToAngle(-90), 
				new DriveRotateToAngle(90),
				new DriveRotateToAngle(-90), 
				new DriveRotateToAngle(90)));
	}
}
