package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale extends CommandGroup {

	public AutoRightSideScale() {
		// part 1
		addSequential(new DriveZeroYaw());
		addParallel(new IntakePneumaticsDown());
		addParallel(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new AutoDriveDistanceAngleFast("RightScaleDist", "RightScaleAngle_1"));
		addSequential(new DriveRotateToAngle("RightScaleAngle_2"));

		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));

		addParallel(new LiftSoftLanding());
		addSequential(new DriveRotateToAngle("RightScaleAngle_3"));
		addSequential(new DriveRotateToAngle("RightScaleAngle_3"));

		// part 2
		addParallel(new AutoDriveDistanceAngleFast("RightScaleDist_2","RightScaleAngle_3"));
		addSequential(new IntakeIntakeCube(4));
		addParallel(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new AutoDriveDistanceAngleFast("RightScaleDist_3","RightScaleAngle_3"));
		addSequential(new DriveRotateToAngle("RightScaleAngle_2"));

		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));
		
		addParallel(new LiftSoftLanding());
		addSequential(new DriveRotateToAngle(0));
		addSequential(new DriveRotateToAngle(0));

	}
}
