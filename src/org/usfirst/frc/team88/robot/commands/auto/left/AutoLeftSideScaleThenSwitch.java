package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.LiftSoftLanding;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistance;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideScaleThenSwitch extends CommandGroup {

	public AutoLeftSideScaleThenSwitch() {
		addSequential(new AutoLeftSideScale());
		
		addParallel(new AutoDriveDistanceAngleFast("LeftTwoCubeDist_1", "LeftTwoCubeAngle_1"));
		addSequential(new IntakeIntakeCube(5));
		addParallel(new LiftGotoPosition(Lift.POS_SWITCH));
		addParallel(new AutoDriveDistanceAngleFast("LeftTwoCubeDist_2", "LeftTwoCubeAngle_2"));
		addSequential(new IntakeEjectCube(Lift.POS_SWITCH));
		addSequential(new AutoDriveDistance(-24));
		addParallel(new LiftSoftLanding());
		addSequential(new DriveRotateToAngle(120));
	}

}
