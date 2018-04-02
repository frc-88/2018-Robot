package org.usfirst.frc.team88.robot.commands.auto.center;

import org.usfirst.frc.team88.robot.commands.HaveCubeConditionalCommand;
import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter extends CommandGroup {

	public AutoCenter() {
		addSequential(new StepConditionalCommand(new AutoCenter_Part1(), "AutoCenterStep", 1));
		addSequential(new StepConditionalCommand(new AutoCenter_Part2(), "AutoCenterStep", 2));
		addSequential(new StepConditionalCommand(new HaveCubeConditionalCommand(new AutoCenter_Part3()), "AutoCenterStep", 2));
	}
}
