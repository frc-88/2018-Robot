package org.usfirst.frc.team88.robot.commands;

public class AutoRight extends PowerUpConditionalCommand {

	public AutoRight() {
		super(	new AutoRightSidePark(),   //LL
				new AutoRightSideScale(),  //LR
				new AutoRightSidePark(), //RL
				new AutoRightSideScale()); //RR
	}

}
