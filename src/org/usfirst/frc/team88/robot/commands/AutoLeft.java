package org.usfirst.frc.team88.robot.commands;

public class AutoLeft extends PowerUpConditionalCommand {

	public AutoLeft() {
		super(	new AutoLeftSideScale(),     //LL
				new AutoLeftSidePark(),  //LR
				new AutoLeftSideScale(),     //RL
				new AutoLeftSidePark()); //RR
	}

}
