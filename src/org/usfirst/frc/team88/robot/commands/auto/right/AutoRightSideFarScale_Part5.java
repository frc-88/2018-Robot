package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.ArmDown;
import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScale_Part5 extends CommandGroup {

	public AutoRightSideFarScale_Part5() {
		// Step 5: Score the second cube

		addParallel(new DriveRotateToAngle("RightFarScaleAngle_6"));
		addParallel(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));
		addParallel(new ArmDown());
	}
}
