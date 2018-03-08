package org.usfirst.frc.team88.robot.commands;

public class AutoRight extends PowerUpConditionalCommand {

	public AutoRight() {
		super(	new AutoRightSideFarScale(),   //LL
				new AutoRightSideScale(),  //LR
				new AutoRightSideFarScale(), //RL
				new AutoRightSideScale()); //RR
	}

}
