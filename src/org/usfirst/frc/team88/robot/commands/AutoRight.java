package org.usfirst.frc.team88.robot.commands;

public class AutoRight extends PowerUpConditionalCommand {

	public AutoRight() {
		super(	new AutoRightSideL(), 
				new AutoRightSideScale(), 
				new AutoRightSideSwitch(),
				new AutoRightSideScaleThenSwitch());
	}

}
