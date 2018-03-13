package org.usfirst.frc.team88.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCrossTheLine extends CommandGroup {

	public AutoCrossTheLine() {
		addSequential(new AutoDriveDistanceAngle(124, 0));
		addSequential(new IntakePneumaticsDown());
	}
}
