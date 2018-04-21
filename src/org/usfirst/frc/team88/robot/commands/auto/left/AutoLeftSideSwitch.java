package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.arm.ArmDown;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.commands.drive.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.drive.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.intake.IntakeEjectCube;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideSwitch extends CommandGroup {

	public AutoLeftSideSwitch() {
		addSequential(new DriveZeroYaw());
		addSequential(new AutoDriveDistanceAngleFast("LeftSwitchDist_1", 0));
		addSequential(new DriveRotateToAngle(90));
		addSequential(new AutoDriveDistanceAngleFast("LeftSwitchDist_2", 90));
		addSequential(new IntakeEjectCube(Lift.POS_SWITCH));
		addParallel(new ArmDown());
		addSequential(new AutoDriveDistanceAngleFast("LeftSwitchDist_3", 180));
	}
}
