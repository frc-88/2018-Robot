package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.HaveCubeConditionalCommand;
import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;
import org.usfirst.frc.team88.robot.commands.drive.DriveDisableBrakeMode;
import org.usfirst.frc.team88.robot.commands.drive.DriveEnableBrakeMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale extends CommandGroup {

	public AutoRightSideScale() {
		//Near Scale: 
		//	Step 1: Score first and gets second cube
		
		//  IF WE PICK UP SECOND CUBE WE DO
		
		//	Step 2: Get in position to score second
		//	Step 3: Score second
		//	Step 4: Drive under scale
		addSequential(new DriveEnableBrakeMode());
		addSequential(new StepConditionalCommand(new AutoRightSideScale_Part1(), "AutoRightSideScaleStep", 1));
		addSequential(new StepConditionalCommand(new AutoRightSideScale_Part2(), "AutoRightSideScaleStep", 2));
		addSequential(new HaveCubeConditionalCommand(new AutoRightSideScale_HasCube()));
		addSequential(new DriveDisableBrakeMode());
	}
}
