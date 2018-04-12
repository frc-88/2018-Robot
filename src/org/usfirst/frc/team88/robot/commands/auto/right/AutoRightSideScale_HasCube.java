package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.HaveCubeConditionalCommand;
import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale_HasCube extends CommandGroup {

    public AutoRightSideScale_HasCube() {
    	addSequential(new StepConditionalCommand(new AutoRightSideScale_Part3(), "AutoRightSideScaleStep", 3));
		addSequential(new StepConditionalCommand(new AutoRightSideScale_Part4(), "AutoRightSideScaleStep", 4));
		addSequential(new StepConditionalCommand(new AutoRightSideScale_Part5(), "AutoRightSideScaleStep", 5));
    }
}
