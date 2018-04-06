package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.ArmStart;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScale_Part4 extends CommandGroup {

    public AutoLeftSideFarScale_Part4() {
    	//Step 4: Prepare to score the second cube
		addParallel(new ArmStart());
		addSequential(new AutoDriveDistanceAngleFast("LeftFarScaleDist_5", "LeftFarScaleAngle_5"));
    }
}
