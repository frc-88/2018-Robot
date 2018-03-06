package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScale extends CommandGroup {

	public AutoRightSideFarScale() {
		addSequential(new DriveZeroYaw());
		addParallel(new IntakePneumaticsDown());
		addSequential(new AutoDriveDistanceAngleFast("LeftFarScaleDist_1", 0));
		addSequential(new DriveRotateToAngle(90));
		addSequential(new AutoDriveDistanceAngleFast("LeftFarScaleDist_2", 90));
		addParallel(new DriveRotateToAngle(0));

		addSequential(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new LiftCheckOnTarget(Lift.POS_HI_SCALE));
		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));

		addParallel(new LiftSoftLanding());
		addSequential(new AutoDriveDistance(-10));
		addSequential(new DriveRotateToAngle(180));
	}
}
