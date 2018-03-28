package org.usfirst.frc.team88.robot.commands.auto.center;

import org.usfirst.frc.team88.robot.commands.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.PowerUpConditionalCommand;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter_Part2 extends CommandGroup {

	public AutoCenter_Part2() {
		// go get the second cube
		addParallel(new PowerUpConditionalCommand(new AutoDriveDistance(45),
				new AutoDriveDistance(45),
				new AutoDriveDistance(36),
				new AutoDriveDistance(36)));
		addSequential(new IntakeIntakeCube(2));
	}
}
