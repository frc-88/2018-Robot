package org.usfirst.frc.team88.robot.commands.auto;

import org.usfirst.frc.team88.robot.commands.arm.ArmDown;
import org.usfirst.frc.team88.robot.commands.intake.IntakePneumaticsDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCrossTheLine extends CommandGroup {

	public AutoCrossTheLine() {
		addSequential(new AutoDriveDistanceAngle(124, 0));
		addSequential(new ArmDown());
	}
}
