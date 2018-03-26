package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideScale_Part2 extends CommandGroup {

    public AutoLeftSideScale_Part2() {
    	//Step 2: Get and be in position to score second cube.
    	addParallel(new AutoDriveDistanceAngleFast("LeftScaleDist_2","LeftScaleAngle_3"));
		addSequential(new IntakeIntakeCube(2));
		addSequential(new AutoDriveDistanceAngleFast("LeftScaleDist_3","LeftScaleAngle_3"));
		addParallel(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new DriveRotateToAngle("LeftScaleAngle_4"));

    }
}
