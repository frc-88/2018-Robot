package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.arm.ArmDown;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngle;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.commands.dingles.DingleDance;
import org.usfirst.frc.team88.robot.commands.dingles.DingleballIn;
import org.usfirst.frc.team88.robot.commands.drive.DriveDisableTURBOMODE;
import org.usfirst.frc.team88.robot.commands.drive.DriveEnableTURBOMODE;
import org.usfirst.frc.team88.robot.commands.drive.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.intake.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.intake.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.lift.LiftGotoPosition;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideScale_Part1 extends CommandGroup {

	public AutoLeftSideScale_Part1() {
		// Step 1: Drive to scale.
		
		addSequential(new DriveZeroYaw());
		// addSequential(new DriveEnableTURBOMODE());
		addSequential(new AutoDriveDistanceAngleFast("LeftScaleDist_1", "LeftScaleAngle_1"));
		// addSequential(new DriveDisableTURBOMODE());

	}
}
