package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistance;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.commands.drive.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.drive.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.intake.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.intake.IntakePneumaticsDown;
import org.usfirst.frc.team88.robot.commands.lift.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.lift.LiftSoftLanding;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideSwitch extends CommandGroup {

	public AutoLeftSideSwitch() {
		addSequential(new DriveZeroYaw());
		addParallel(new IntakePneumaticsDown());
		addSequential(new AutoDriveDistanceAngleFast("LeftSwitchDist_1", 0));
		addParallel(new LiftGotoPosition(Lift.POS_SWITCH));
		addSequential(new DriveRotateToAngle(90));
		addSequential(new AutoDriveDistanceAngleFast("LeftSwitchDist_2", 90));
		addSequential(new IntakeEjectCube(Lift.POS_SWITCH));
		addSequential(new AutoDriveDistance(-24));
		addParallel(new LiftSoftLanding());
		addSequential(new DriveRotateToAngle(0));
		addSequential(new AutoDriveDistanceAngleFast("LeftSwitchDist_3", 0));
	}
}
