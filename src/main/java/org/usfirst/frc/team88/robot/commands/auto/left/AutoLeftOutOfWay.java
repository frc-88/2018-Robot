package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.PowerUpConditionalCommand;

public class AutoLeftOutOfWay extends PowerUpConditionalCommand {

	public AutoLeftOutOfWay() {
		super(new AutoLeftOOWNear(), // LL
				new AutoLeftSideSwitch(), // LR
				new AutoLeftOOWNear(), // RL
				new AutoLeftOOWFar()); // RR
	}

}
