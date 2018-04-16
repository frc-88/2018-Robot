package org.usfirst.frc.team88.robot.commands.auto.center;

import org.usfirst.frc.team88.robot.commands.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.PowerUpConditionalCommand;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistance;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter_Part2 extends CommandGroup {

	public AutoCenter_Part2() {
		// go get the second cube
		addParallel(new PowerUpConditionalCommand(new AutoDriveDistanceAngleFast(45,50),
				new AutoDriveDistanceAngleFast(45,50),
				new AutoDriveDistanceAngleFast(36,-50),
				new AutoDriveDistanceAngleFast(36,-50)));
		addSequential(new IntakeIntakeCube(2));
	}
}
