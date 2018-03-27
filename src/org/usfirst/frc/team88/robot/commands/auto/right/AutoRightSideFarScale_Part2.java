package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.LiftCheckOnTarget;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngle;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScale_Part2 extends CommandGroup {

    public AutoRightSideFarScale_Part2() {
    	//Step 2: Prepare First Cube
    	
    	addSequential(new DriveRotateToAngle("RightFarScaleAngle_1"));
		addParallel(new DriveRotateToAngle("RightFarScaleAngle_1"));
		addSequential(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new LiftCheckOnTarget(Lift.POS_HI_SCALE));
		addSequential(new AutoDriveDistanceAngle("RightFarScaleDist_3", 0));
    }
}
