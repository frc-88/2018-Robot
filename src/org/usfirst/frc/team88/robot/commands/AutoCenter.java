package org.usfirst.frc.team88.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter extends CommandGroup {

	public AutoCenter() {
		addSequential(new AutoCenterToSwitch());
		addSequential(new AutoDriveDistance(-24));
		addSequential(new LiftSoftLanding());
	}
}
