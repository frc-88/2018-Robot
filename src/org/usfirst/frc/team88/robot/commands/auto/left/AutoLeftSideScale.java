package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.IntakePneumaticsDown;
import org.usfirst.frc.team88.robot.commands.LiftCheckOnTarget;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.LiftSoftLanding;
import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistance;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.commands.auto.right.AutoRightSideScale_Part1;
import org.usfirst.frc.team88.robot.commands.auto.right.AutoRightSideScale_Part2;
import org.usfirst.frc.team88.robot.commands.auto.right.AutoRightSideScale_Part3;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideScale extends CommandGroup {

	public AutoLeftSideScale() {
		//Near Scale: 
		//	Step 1: Score first Cube
		//	Step 2: Get and be in position to score second cube.
		//	Step 3: Score second cube
		addSequential(new StepConditionalCommand(new AutoLeftSideScale_Part1(), "AutoLeftSideScaleStep", 1));
		addSequential(new StepConditionalCommand(new AutoLeftSideScale_Part2(), "AutoLeftSideScaleStep", 2));
		addSequential(new StepConditionalCommand(new AutoLeftSideScale_Part3(), "AutoLeftSideScaleStep", 3));
		
	}
}
