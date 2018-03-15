package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScaleThenSwitch extends CommandGroup {

	public AutoRightSideScaleThenSwitch() {
		addSequential(new AutoRightSideScale());
		
		addParallel(new AutoDriveDistanceAngleFast("RightTwoCubeDist_1", "RightTwoCubeAngle_1"));
		addSequential(new IntakeIntakeCube(5));
		addParallel(new LiftGotoPosition(Lift.POS_SWITCH));
		addParallel(new AutoDriveDistanceAngleFast("RightTwoCubeDist_2", "RightTwoCubeAngle_2"));
		addSequential(new IntakeEjectCube(Lift.POS_SWITCH));
		addSequential(new AutoDriveDistance(-24));
		addParallel(new LiftSoftLanding());
		addSequential(new DriveRotateToAngle(-120));
	}
}
