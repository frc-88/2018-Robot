package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.IntakePneumaticsDown;
import org.usfirst.frc.team88.robot.commands.LiftCheckOnTarget;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.LiftSoftLanding;
import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScale extends CommandGroup {

	public AutoRightSideFarScale() {
		//Far Side Near Switch:
		//	Step 1: Drive L
		//	Step 2: Prepare First Cube
		//	Step 3: Score First Cube
		//	Step 4: Drive under scale
		
		addSequential(new StepConditionalCommand(new AutoRightSideFarScale_Part1(), "AutoRightSideFarScaleStep", 1));
		addSequential(new StepConditionalCommand(new AutoRightSideFarScale_Part2(), "AutoRightSideFarScaleStep", 2));
		addSequential(new StepConditionalCommand(new AutoRightSideFarScale_Part3(), "AutoRightSideFarScaleStep", 3));
		addSequential(new StepConditionalCommand(new AutoRightSideFarScale_Part4(), "AutoRightSideFarScaleStep", 4));
	}
}
