package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale_Part2 extends CommandGroup {

    public AutoRightSideScale_Part2() {
    	//Step 2: Get and be in position to score second cube.
    	
    	addParallel(new AutoDriveDistanceAngleFast("RightScaleDist_2","RightScaleAngle_3"));
		addSequential(new IntakeIntakeCube(4));
		addParallel(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new AutoDriveDistanceAngleFast("RightScaleDist_3","RightScaleAngle_3"));
		addSequential(new DriveRotateToAngle("RightScaleAngle_2"));

    }
}
