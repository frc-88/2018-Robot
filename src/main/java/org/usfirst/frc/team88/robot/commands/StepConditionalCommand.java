package org.usfirst.frc.team88.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class StepConditionalCommand extends ConditionalCommand {
	
	String name;
	int step;
	public StepConditionalCommand(Command command, String name, int step){
		super(command);
		this.name = name;
		this.step = step;
	}

	@Override
	protected boolean condition() {
		// TODO Auto-generated method stub
		Preferences prefs = Preferences.getInstance();
		
		return prefs.getInt(name, 1) >= step;

	}

}
