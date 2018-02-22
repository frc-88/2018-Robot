package org.usfirst.frc.team88.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class AutoLeft extends PowerUpConditionalCommand {

	public AutoLeft(Command LLCommand, Command LRCommand, Command RLCommand, Command RRCommand) {
		super(new AutoLeftSideL(), new AutoLeftSideScale(), new AutoLeftSideSwitch(),
				new AutoLeftSideScaleThenSwitch());
	}

}
