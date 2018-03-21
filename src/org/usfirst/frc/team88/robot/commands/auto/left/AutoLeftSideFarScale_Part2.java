package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.LiftCheckOnTarget;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScale_Part2 extends CommandGroup {

    public AutoLeftSideFarScale_Part2() {
    	//Step 2: Prepare First Cube
    	
    	addSequential(new DriveRotateToAngle("LeftFarScaleAngle_1"));
		addParallel(new DriveRotateToAngle("LeftFarScaleAngle_1"));
		addSequential(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new LiftCheckOnTarget(Lift.POS_LOW_SCALE));
		addSequential(new AutoDriveDistanceAngleFast("LeftFarScaleDist_3", 0));
    }
}
