package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.ArmDown;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngle;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScale_Part3 extends CommandGroup {

	public AutoRightSideFarScale_Part3() {
		// Step 3: Score First Cube, go get the second

		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));
		addParallel(new ArmDown());
		addParallel(new AutoDriveDistanceAngle("RightFarScaleDist_4", "RightFarScaleAngle_4"));
		addSequential(new IntakeIntakeCube(4));
	}
}
