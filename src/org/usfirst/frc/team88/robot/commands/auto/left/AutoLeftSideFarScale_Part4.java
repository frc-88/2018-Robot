package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScale_Part4 extends CommandGroup {

    public AutoLeftSideFarScale_Part4() {
    	//Step 4: Drive under scale
    	
    	addSequential(new AutoDriveDistanceAngleFast("LeftFarScaleDist_4", 0));
    }
}
