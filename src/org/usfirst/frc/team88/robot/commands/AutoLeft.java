package org.usfirst.frc.team88.robot.commands;

public class AutoLeft extends PowerUpConditionalCommand {

	public AutoLeft() {
		super(new AutoLeftSideL(), new AutoLeftSideScale(), new AutoLeftSideSwitch(),
				new AutoLeftSideScaleThenSwitch());
	}

}
