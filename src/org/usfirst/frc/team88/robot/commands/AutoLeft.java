package org.usfirst.frc.team88.robot.commands;

public class AutoLeft extends PowerUpConditionalCommand {

	public AutoLeft() {
		super(	new AutoLeftSideScaleThenSwitch(),
				new AutoLeftSideSwitch(),
				new AutoLeftSideScale(), 
				new AutoLeftSideL());
	}

}
