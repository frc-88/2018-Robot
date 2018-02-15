package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LiftGotoPosition extends InstantCommand {

	private int target;

	public LiftGotoPosition(int target) {
		super();

		this.target = target;

		requires(Robot.lift);
	}

	// Called once when the command executes
	protected void initialize() {
		Robot.lift.setPosition(target);
		Robot.lift.gotoPosition();
	}
}
