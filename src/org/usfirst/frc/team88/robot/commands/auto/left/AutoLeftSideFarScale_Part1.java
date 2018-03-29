package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.IntakePneumaticsDown;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceTurn;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScale_Part1 extends CommandGroup {

    public AutoLeftSideFarScale_Part1() {
    	//Step 1: Drive L
    	
    	addSequential(new DriveZeroYaw());
		addParallel(new IntakePneumaticsDown());
		addParallel(new LiftGotoPosition(Lift.POS_ALMOST_BOTTOM));
		
		addSequential(new AutoDriveDistanceTurn("LeftFarScaleDist_1","LeftFarScaleDist_2",true));
		
		//addSequential(new AutoDriveDistanceAngleFast("LeftFarScaleDist_1", 0));
		//addSequential(new DriveRotateToAngle(90));
		//addSequential(new AutoDriveDistanceAngleFast("LeftFarScaleDist_2", 90));
    }
}
