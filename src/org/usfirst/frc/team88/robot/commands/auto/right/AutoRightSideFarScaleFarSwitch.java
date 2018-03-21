package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScaleFarSwitch extends CommandGroup {

    public AutoRightSideFarScaleFarSwitch() {
    	//Far Side Far Switch:
    	//	Step 1: Drive L
    	//	Step 2: Prepare First Cube
    	//	Step 3: Score First Cube
    	//	Skipping 4
    	//	Step 5: Prepare Second Cube
    	//	Step 6: Score Second Cube in Switch
    	addSequential(new StepConditionalCommand(new AutoRightSideFarScale_Part1(), "AutoRightSideFarScaleStep", 1));
		addSequential(new StepConditionalCommand(new AutoRightSideFarScale_Part2(), "AutoRightSideFarScaleStep", 2));
		addSequential(new StepConditionalCommand(new AutoRightSideFarScale_Part3(), "AutoRightSideFarScaleStep", 3));
		addSequential(new StepConditionalCommand(new AutoRightSideFarScaleFarSwitch_Part5(), "AutoRightSideFarScaleStep", 5));
		addSequential(new StepConditionalCommand(new AutoRightSideFarScaleFarSwitch_Part6(), "AutoRightSideFarScaleStep", 6));
    }
}
