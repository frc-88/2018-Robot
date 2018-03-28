package org.usfirst.frc.team88.robot.commands.auto.center;

import org.usfirst.frc.team88.robot.commands.HaveCubeConditionalCommand;
import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter extends CommandGroup {

	public AutoCenter() {
		// step 1:
		//    score preloaded cube into the correct switch
		//    back off and soft land
		//    turn towards a second cube
		//
		// step 2:
		//    go get the second cube
		//    if we have a cube, back off and turn towards our scale

		addSequential(new StepConditionalCommand(new AutoCenter_Part1(), "AutoCenterStep", 1));
		addSequential(new StepConditionalCommand(new AutoCenter_Part2(), "AutoCenterStep", 2));
		addSequential(new StepConditionalCommand(new HaveCubeConditionalCommand(new AutoCenter_Part3()), "AutoCenterStep", 2));
	}
}
