package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.ArmDown;
import org.usfirst.frc.team88.robot.commands.DingleDance;
import org.usfirst.frc.team88.robot.commands.DingleballIn;
import org.usfirst.frc.team88.robot.commands.DriveDisableTURBOMODE;
import org.usfirst.frc.team88.robot.commands.DriveEnableTURBOMODE;
import org.usfirst.frc.team88.robot.commands.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngle;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
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
