package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class IntakePneumaticsUp extends InstantCommand {

    public IntakePneumaticsUp() {
        super();
       
        requires(Robot.intake);
    }

    // Called once when the command executes
    protected void initialize() {
    	
    	Robot.intake.cradleUp();
    	
    }

}
