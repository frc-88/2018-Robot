package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class intakePneumaticsUp extends InstantCommand {

    public intakePneumaticsUp() {
        super();
       
        requires(Robot.intake);
    }

    // Called once when the command executes
    protected void initialize() {
    	
    	Robot.intake.intakeCradleUp();
    	
    }

}
