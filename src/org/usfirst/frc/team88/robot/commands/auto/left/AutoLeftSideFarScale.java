package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScale extends CommandGroup {

	public AutoLeftSideFarScale() {
		//Far Side Near Switch:
		//	Step 1: Drive L
		// 	Step 2: Prepare First Cube
		// 	Step 3: Score First Cube
		// 	Step 4: Drive under scale

		addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part1(), "AutoLeftSideFarScaleStep", 1));
		addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part2(), "AutoLeftSideFarScaleStep", 2));
		addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part3(), "AutoLeftSideFarScaleStep", 3));
		addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part4(), "AutoLefttSideFarScaleStep", 4));
	}
}
