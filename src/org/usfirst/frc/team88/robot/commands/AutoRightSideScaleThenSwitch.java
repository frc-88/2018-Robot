package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScaleThenSwitch extends CommandGroup {

	public AutoRightSideScaleThenSwitch() {
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

		addSequential(new DriveRotateToAngle(-125));
		addParallel(new AutoDriveDistanceAngle(64, -147));
		addSequential(new IntakeIntakeCube(5));
		addParallel(new LiftGotoPosition(Lift.POS_SWITCH));
		addSequential(new AutoDriveDistanceAngle(12, -147));
		addSequential(new IntakeEjectCube(Lift.POS_SWITCH));
		// addSequential(new AutoDriveDistance(-20));
		// addParallel(new LiftSoftLanding());
		// addSequential(new DriveRotateToAngle(0));

		addSequential(new DriveDisableBreakMode());
	}

}
