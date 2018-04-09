package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.ArmStart;
import org.usfirst.frc.team88.robot.commands.Delay;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideScale_Part3 extends CommandGroup {

	public AutoLeftSideScale_Part3() {
		// Step 2: Get in position to score second
		addParallel(new AutoDriveDistanceAngleFast("LeftScaleDist_3", "LeftScaleAngle_3"));
		addSequential(new Delay(20));
		addSequential(new ArmStart());
		

	}
}
