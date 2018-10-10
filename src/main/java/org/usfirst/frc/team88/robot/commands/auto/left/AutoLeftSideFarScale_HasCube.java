package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScale_HasCube extends CommandGroup {

    public AutoLeftSideFarScale_HasCube() {
		addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part4(), "AutoLeftSideFarScaleStep", 4));
		addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part5(), "AutoLeftSideFarScaleStep", 5));
		addSequential(new StepConditionalCommand(new AutoLeftSideFarScale_Part6(), "AutoLeftSideFarScaleStep", 6));
	}
}
