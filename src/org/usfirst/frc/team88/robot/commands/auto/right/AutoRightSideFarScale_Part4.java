package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScale_Part4 extends CommandGroup {

    public AutoRightSideFarScale_Part4() {
    	//Step 4: Drive under scale
    	
    	addSequential(new AutoDriveDistanceAngleFast("RightFarScaleDist_4", 0));
    }
}
