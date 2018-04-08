package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.HaveCubeConditionalCommand;
import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideScale extends CommandGroup {

	public AutoLeftSideScale() {
		//Near Scale: 
		//	Step 1: Score first and gets second cube
		
		//  IF WE PICK UP SECOND CUBE WE DO
		
		//	Step 2: Get in position to score second
		//	Step 3: Score second
		//	Step 4: Drive under scale
		addSequential(new StepConditionalCommand(new AutoLeftSideScale_Part1(), "AutoLeftSideScaleStep", 1));
		addSequential(new StepConditionalCommand(new AutoLeftSideScale_Part2(), "AutoLeftSideScaleStep", 2));
		addSequential(new HaveCubeConditionalCommand(new AutoLeftSideScale_HasCube()));
	}
}
