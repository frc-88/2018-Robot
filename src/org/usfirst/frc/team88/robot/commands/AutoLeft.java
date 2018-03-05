package org.usfirst.frc.team88.robot.commands;

public class AutoLeft extends PowerUpConditionalCommand {

	public AutoLeft() {
		super(	new AutoDriveToNullAndLift(), //LL
				new AutoDriveDistanceAngle(19*12, 0), //LR
				new AutoDriveToNullAndLift(),  //RL
				new AutoDriveDistanceAngle(19*12, 0));	  //RR
	}

}
