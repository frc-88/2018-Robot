package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScaleFarSwitch extends CommandGroup {

    public AutoLeftSideFarScaleFarSwitch() {
    	//Far Side Far Switch:
    	//	Step 1: Drive L
    	//	Step 2: Prepare First Cube
    	//	Step 3: Score First Cube
    	//	Skipping 4
    	//	Step 5: Prepare Second Cube
    	//	Step 6: Score Second Cube in Switch
    	addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part1(), "AutoLeftSideFarScaleStep", 1));
		addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part2(), "AutoLeftSideFarScaleStep", 2));
		addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part3(), "AutoLeftSideFarScaleStep", 3));
		addSequential(new StepConditionalCommand(new AutoLeftSideFarScaleFarSwitch_Part5(), "AutoLeftSideFarScaleStep", 5));
		addSequential(new StepConditionalCommand(new AutoLeftSideFarScaleFarSwitch_Part6(), "AutoLeftSideFarScaleStep", 6));
    }
}
