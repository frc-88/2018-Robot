package org.usfirst.frc.team88.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class AutoRight extends PowerUpConditionalCommand {

	public AutoRight(Command LLCommand, Command LRCommand, Command RLCommand, Command RRCommand) {
		super(new AutoRightSideL(), new AutoRightSideScale(), new AutoRightSideSwitch(),
				new AutoRightSideScaleThenSwitch());
	}

}
