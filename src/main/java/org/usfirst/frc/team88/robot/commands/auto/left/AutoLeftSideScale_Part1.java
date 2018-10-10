package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.commands.drive.DriveDisableTURBOMODE;
import org.usfirst.frc.team88.robot.commands.drive.DriveEnableTURBOMODE;
import org.usfirst.frc.team88.robot.commands.drive.DriveZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideScale_Part1 extends CommandGroup {

	public AutoLeftSideScale_Part1() {
		// Step 1: Drive to scale.
		
		addSequential(new DriveZeroYaw());
		// addSequential(new DriveEnableTURBOMODE());
		addSequential(new AutoDriveDistanceAngleFast("LeftScaleDist_1", "LeftScaleAngle_1"));
		// addSequential(new DriveDisableTURBOMODE());

	}
}
