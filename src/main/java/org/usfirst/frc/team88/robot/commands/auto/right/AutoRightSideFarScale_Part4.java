package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.arm.ArmStart;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScale_Part4 extends CommandGroup {

	public AutoRightSideFarScale_Part4() {
		// Step 4: Prepare to score the second cube
		addParallel(new ArmStart());
		addSequential(new AutoDriveDistanceAngleFast("LeftFarScaleDist_5", "LeftFarScaleAngle_5"));
	}
}
