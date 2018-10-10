package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngle;
import org.usfirst.frc.team88.robot.commands.drive.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.lift.LiftGotoPosition;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScale_Part2 extends CommandGroup {

    public AutoLeftSideFarScale_Part2() {
    	//Step 2: Prepare First Cube
    	
		addParallel(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new DriveRotateToAngle("LeftFarScaleAngle_2"));
		addSequential(new AutoDriveDistanceAngle("LeftFarScaleDist_3", "LeftFarScaleAngle_3"));
    }
}
