package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.ArmStart;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideScale_Part2 extends CommandGroup {

	public AutoLeftSideScale_Part2() {
		// Step 2: Get in position to score second
		
		addParallel(new ArmStart());
		addSequential(new AutoDriveDistanceAngleFast("LeftScaleDist_3", "LeftScaleAngle_3"));

	}
}
