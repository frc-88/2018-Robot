package org.usfirst.frc.team88.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideL extends CommandGroup {

	public AutoLeftSideL() {
		addSequential(new DriveZeroYaw());
		addSequential(new AutoDriveDistanceAngle(19.5 * 12, 0));
		addSequential(new DriveRotateToAngle(90));
		addSequential(new DriveRotateToAngle(90));
		addSequential(new AutoDriveDistanceAngle(17.5 * 12, 90));
		addSequential(new DriveRotateToAngle(0));
		addSequential(new AutoDriveDistanceAngle(1.5 * 12 + 3, 0));
	}
}
