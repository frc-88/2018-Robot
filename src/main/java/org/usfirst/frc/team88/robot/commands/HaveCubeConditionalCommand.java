package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class HaveCubeConditionalCommand extends ConditionalCommand {

	public HaveCubeConditionalCommand(Command haveCube) {
		super(haveCube);
	}

	public HaveCubeConditionalCommand(Command haveCube, Command noCube) {
		super(haveCube, noCube);
	}

	public HaveCubeConditionalCommand(String name, Command haveCube) {
		super(name, haveCube);
	}

	public HaveCubeConditionalCommand(String name, Command haveCube, Command noCube) {
		super(name, haveCube, noCube);
	}

	@Override
	protected boolean condition() {
		return Robot.intake.haveCube();
	}

}
