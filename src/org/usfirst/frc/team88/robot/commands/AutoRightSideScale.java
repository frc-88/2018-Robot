package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale extends CommandGroup {

	public AutoRightSideScale() {
		addSequential(new DriveZeroYaw());
		addParallel(new IntakePneumaticsDown());
		addParallel(new LiftGotoPosition(Lift.POS_ALMOST_BOTTOM));
		addSequential(new AutoDriveDistanceAngleFast("RightScaleDist", "RightScaleAngle_1"));
		addSequential(new DriveRotateToAngle("RightScaleAngle_2"));

		addSequential(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new LiftCheckOnTarget(Lift.POS_HI_SCALE));
		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));

		addParallel(new LiftSoftLanding());
		addSequential(new AutoDriveDistance(-10));
		addSequential(new DriveRotateToAngle("RightScaleAngle_3"));
		addSequential(new DriveRotateToAngle("RightScaleAngle_3"));
	}
}
