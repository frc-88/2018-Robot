package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideSwitch extends CommandGroup {

	public AutoRightSideSwitch() {
		addSequential(new DriveZeroYaw());
		addParallel(new IntakePneumaticsDown());
		addSequential(new AutoDriveDistanceAngleFast("RightSwitchDist_1", 0));
		addParallel(new LiftGotoPosition(Lift.POS_SWITCH));
		addSequential(new DriveRotateToAngle(-90));
		addSequential(new AutoDriveDistanceAngleFast("RightSwitchDist_2", -90));
		addSequential(new IntakeEjectCube(Lift.POS_SWITCH));
		addSequential(new AutoDriveDistance(-24));
		addParallel(new LiftSoftLanding());
		addSequential(new DriveRotateToAngle(0));
		addSequential(new AutoDriveDistanceAngleFast("RightSwitchDist_3", 0));
	}
}
