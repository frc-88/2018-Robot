package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceTurn;
import org.usfirst.frc.team88.robot.commands.drive.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.intake.IntakeSuck;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScale_Part1 extends CommandGroup {

    public AutoLeftSideFarScale_Part1() {
    	//Step 1: Drive L
    	
    	addSequential(new DriveZeroYaw());
		addParallel(new IntakeSuck());
    	addSequential(new AutoDriveDistanceTurn("LeftFarScaleDist_1", "LeftFarScaleDist_2", "LeftFarScaleAngle_1"));
    }
}
