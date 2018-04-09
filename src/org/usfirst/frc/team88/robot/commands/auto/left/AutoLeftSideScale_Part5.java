package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideScale_Part5 extends CommandGroup {

	public AutoLeftSideScale_Part5() {
		// Step 4: Drive under scale

		addSequential(new DriveRotateToAngle(180));
		addSequential(new AutoDriveDistanceAngleFast(24, 180));
	}

}
