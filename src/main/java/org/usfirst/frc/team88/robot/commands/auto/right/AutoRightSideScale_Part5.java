package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.commands.drive.DriveRotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale_Part5 extends CommandGroup {

    public AutoRightSideScale_Part5() {
		// Step 4: Drive under scale

		addSequential(new DriveRotateToAngle(180));
		addSequential(new AutoDriveDistanceAngleFast(24, 180));

    }
}
