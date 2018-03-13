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
		addParallel(new LiftGotoPosition(Lift.POS_ALMOST_BOTTOM));
		addSequential(new AutoDriveDistanceAngleFast("RightFarScaleDist_1", 0));
		addSequential(new DriveRotateToAngle(-90));
		addSequential(new AutoDriveDistanceAngleFast("RightFarScaleDist_2", -90));
	}
}
