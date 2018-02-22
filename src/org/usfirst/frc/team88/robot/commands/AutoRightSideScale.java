package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale extends CommandGroup {

	public AutoRightSideScale() {
		addSequential(new DriveZeroYaw());
		addSequential(new DriveEnableBreakMode());
		addParallel(new IntakePneumaticsDown());
		addSequential(new AutoDriveDistanceAngleFast(25.25 * 12, 0));
		addSequential(new DriveRotateToAngle(-45));
		// addSequential(new AutoDriveDistanceAngle(2, -45));

		addSequential(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new LiftCheckOnTarget(Lift.POS_HI_SCALE));
		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));

		addParallel(new LiftSoftLanding());
		addSequential(new AutoDriveDistance(-10));
	}
}
