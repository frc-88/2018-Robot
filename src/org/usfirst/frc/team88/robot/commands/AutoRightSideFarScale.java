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
		addParallel(new LiftGotoPosition(Lift.POS_ALMOST_BOTTOM));
		addSequential(new AutoDriveDistanceAngleFast("RightFarScaleDist_1", 0));
		addSequential(new DriveRotateToAngle(-90));
		addSequential(new AutoDriveDistanceAngleFast("RightFarScaleDist_2", -90));
		
		
		addSequential(new DriveRotateToAngle("RightFarScaleAngle_1"));
		addParallel(new DriveRotateToAngle("RightFarScaleAngle_1"));
		addSequential(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new LiftCheckOnTarget(Lift.POS_LOW_SCALE));
		addSequential(new AutoDriveDistanceAngleFast("RightFarScaleDist_3", 0));
		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE , .3));

		addSequential(new AutoDriveDistance(-36));
		addParallel(new LiftSoftLanding());
		addSequential(new DriveRotateToAngle(180));
	}
}
