package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngle;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScale_Part2 extends CommandGroup {

	public AutoRightSideFarScale_Part2() {
		// Step 2: Prepare First Cube

		addParallel(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new DriveRotateToAngle("RightFarScaleAngle_2"));
		addSequential(new AutoDriveDistanceAngle("RightFarScaleDist_3", "RightFarScaleAngle_3"));
	}
}
