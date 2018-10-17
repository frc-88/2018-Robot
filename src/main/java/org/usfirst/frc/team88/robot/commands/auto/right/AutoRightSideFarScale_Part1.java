package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceTurn;
import org.usfirst.frc.team88.robot.commands.drive.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.intake.IntakeSuck;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScale_Part1 extends CommandGroup {

	public AutoRightSideFarScale_Part1() {
		// Step 1: Drive L

		addSequential(new DriveZeroYaw());
		addParallel(new IntakeSuck());
		addSequential(new AutoDriveDistanceTurn("RightFarScaleDist_1", "RightFarScaleDist_2", "RightFarScaleAngle_1"));
	}
}
