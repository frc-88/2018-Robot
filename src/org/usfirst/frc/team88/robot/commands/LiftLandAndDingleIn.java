package org.usfirst.frc.team88.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LiftLandAndDingleIn extends CommandGroup {

	public LiftLandAndDingleIn() {
		addParallel(new LiftSoftLanding());
		addParallel(new DingleballIn());
	}
}
