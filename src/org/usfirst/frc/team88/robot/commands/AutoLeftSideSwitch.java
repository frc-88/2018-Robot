package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideSwitch extends CommandGroup {

	public AutoLeftSideSwitch() {
		addSequential(new DriveZeroYaw());
		addParallel(new LiftGotoPosition(Lift.POS_SWITCH));
		addParallel(new IntakePneumaticsDown());
		addSequential(new AutoDriveDistanceAngle(156, 0));
		addSequential(new DriveRotateToAngle(70));
		addSequential(new AutoDriveDistanceAngle(24, 90));
		addSequential(new IntakeEjectCube(Lift.POS_SWITCH));
		addSequential(new AutoDriveDistance(-24));
		addParallel(new LiftSoftLanding());
		addSequential(new DriveRotateToAngle(20));
		addSequential(new AutoDriveDistanceAngle(24, 0));
	}
}
