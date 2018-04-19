package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.commands.drive.DriveRotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScale_Part6 extends CommandGroup {

	public AutoRightSideFarScale_Part6() {
		// Step 6: Drive under scale

		addSequential(new DriveRotateToAngle(180));
		addSequential(new AutoDriveDistanceAngleFast(24, 180));
	}
}
