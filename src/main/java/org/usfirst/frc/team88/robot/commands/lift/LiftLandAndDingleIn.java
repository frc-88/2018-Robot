package org.usfirst.frc.team88.robot.commands.lift;

import org.usfirst.frc.team88.robot.commands.dingles.DingleballIn;

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
