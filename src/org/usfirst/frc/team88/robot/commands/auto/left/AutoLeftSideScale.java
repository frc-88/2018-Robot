package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

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
