package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideScale extends CommandGroup {

	public AutoLeftSideScale() {
		addSequential(new DriveZeroYaw());
		addParallel(new IntakePneumaticsDown());
		addParallel(new LiftGotoPosition(Lift.POS_ALMOST_BOTTOM));
		addSequential(new AutoDriveDistanceAngleFast("LeftScaleDist", "LeftScaleAngle_1"));
		addSequential(new DriveRotateToAngle("LeftScaleAngle_2"));

		addSequential(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new LiftCheckOnTarget(Lift.POS_HI_SCALE));
		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));

		addParallel(new LiftSoftLanding());
		addSequential(new AutoDriveDistance(-10));
		addSequential(new DriveRotateToAngle("LeftScaleAngle_3"));
		addSequential(new DriveRotateToAngle("LeftScaleAngle_3"));
	}
}
