package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.DriveDisableTURBOMODE;
import org.usfirst.frc.team88.robot.commands.DriveEnableTURBOMODE;
import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.IntakePneumaticsDown;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.LiftSoftLanding;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale_Part1 extends CommandGroup {

    public AutoRightSideScale_Part1() {
    	// Step 1: Drive to scale.
		
    	addSequential(new DriveZeroYaw());
		//addSequential(new DriveEnableTURBOMODE());
		addSequential(new AutoDriveDistanceAngleFast("RightScaleDist_1", "RightScaleAngle_1"));
		//addSequential(new DriveDisableTURBOMODE());
    }
}
