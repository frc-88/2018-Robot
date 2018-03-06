package org.usfirst.frc.team88.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSidePark extends CommandGroup {

	public AutoLeftSidePark() {
		addSequential(new DriveZeroYaw());
		addParallel(new IntakePneumaticsDown());
		addSequential(new AutoDriveDistanceAngleFast("LeftParkDist_1", 0));
		addSequential(new DriveRotateToAngle(90));
		addSequential(new AutoDriveDistanceAngleFast("LeftParkDist_2", 90));	}
}
