package org.usfirst.frc.team88.robot.commands;

public class AutoRight extends PowerUpConditionalCommand {

	public AutoRight() {
		super(	new AutoDriveDistanceAngleFast(18*12, 0), //LL
				new AutoDriveToNullAndLift(), //LR
				new AutoDriveDistanceAngleFast(19*12, 0), //RL
				new AutoDriveToNullAndLift()); //RR
	}

}
