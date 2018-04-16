package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScale_HasCube extends CommandGroup {

    public AutoRightSideFarScale_HasCube() {
		addSequential(new StepConditionalCommand(new AutoRightSideFarScale_Part4(), "AutoRightSideFarScaleStep", 4));
		addSequential(new StepConditionalCommand(new AutoRightSideFarScale_Part5(), "AutoRightSideFarScaleStep", 5));
		addSequential(new StepConditionalCommand(new AutoRightSideFarScale_Part6(), "AutoRightSideFarScaleStep", 6));
	}
}
