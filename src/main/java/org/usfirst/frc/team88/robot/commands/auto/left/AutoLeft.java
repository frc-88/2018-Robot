package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.PowerUpConditionalCommand;

public class AutoLeft extends PowerUpConditionalCommand {

	public AutoLeft() {
		super(new AutoLeftSideScale(), // LL
				new AutoLeftSideFarScale(), // LR
				new AutoLeftSideScale(), // RL
				new AutoLeftSideFarScale()); // RR
	}

}
