package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSidePark extends CommandGroup {

	public AutoRightSidePark() {
		addSequential(new DriveZeroYaw());
		addParallel(new IntakePneumaticsDown());
		addSequential(new AutoDriveDistanceAngleFast("RightParkDist_1", 0));
		addSequential(new DriveRotateToAngle(-90));
		addSequential(new AutoDriveDistanceAngleFast("RightParkDist_2", -90));
	}
}
