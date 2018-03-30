package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale extends CommandGroup {

	public AutoRightSideScale() {
		//Near Scale: 
		//	Step 1: Score first Cube
		//	Step 2: Get and be in position to score second cube.
		//	Step 3: Score second cube
		addSequential(new StepConditionalCommand(new AutoRightSideScale_Part1(), "AutoRightSideScaleStep", 1));
		addSequential(new StepConditionalCommand(new AutoRightSideScale_Part2(), "AutoRightSideScaleStep", 2));
		addSequential(new StepConditionalCommand(new AutoRightSideScale_Part3(), "AutoRightSideScaleStep", 3));
	}
}
