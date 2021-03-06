package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideScale_HasCube extends CommandGroup {

    public AutoLeftSideScale_HasCube() {
    	addSequential(new StepConditionalCommand(new AutoLeftSideScale_Part3(), "AutoLeftSideScaleStep", 3));
		addSequential(new StepConditionalCommand(new AutoLeftSideScale_Part4(), "AutoLeftSideScaleStep", 4));
		addSequential(new StepConditionalCommand(new AutoLeftSideScale_Part5(), "AutoLeftSideScaleStep", 5));
    }
}
