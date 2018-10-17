package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.HaveCubeConditionalCommand;
import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScale extends CommandGroup {

	public AutoLeftSideFarScale() {
		//Far Side Scale:
		//	Step 1: Drive L
		// 	Step 2: Prepare First Cube
		//  Step 3: Score First Cube, go get the second
		
		//  IF WE PICK UP SECOND CUBE WE DO
		
		//  Step 4: Prepare to score the second cube
		//  Step 5: Score the second cube
		//  Step 6: Drive under scale

		addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part1(), "AutoLeftSideFarScaleStep", 1));
		addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part2(), "AutoLeftSideFarScaleStep", 2));
		addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part3(), "AutoLeftSideFarScaleStep", 3));
		addSequential(new HaveCubeConditionalCommand(new AutoLeftSideFarScale_HasCube()));
	}
}
