package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.commands.drive.DriveDisableTURBOMODE;
import org.usfirst.frc.team88.robot.commands.drive.DriveEnableTURBOMODE;
import org.usfirst.frc.team88.robot.commands.drive.DriveZeroYaw;

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
