package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.ArmDown;
import org.usfirst.frc.team88.robot.commands.DingleDance;
import org.usfirst.frc.team88.robot.commands.DingleballIn;
import org.usfirst.frc.team88.robot.commands.DriveDisableTURBOMODE;
import org.usfirst.frc.team88.robot.commands.DriveEnableTURBOMODE;
import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
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
public class AutoLeftSideScale_Part2 extends CommandGroup {

	public AutoLeftSideScale_Part2() {
		// Step 1.5: Score first cube and get second cube
		
		addParallel(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new DriveRotateToAngle(40));
		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));
		addParallel(new ArmDown());
//		addSequential(new DriveRotateToAngle("LeftScaleAngle_2"));
		addParallel(new AutoDriveDistanceAngleFast("LeftScaleDist_2", "LeftScaleAngle_2"));
		addSequential(new IntakeIntakeCube(4));

	}
}
