package org.usfirst.frc.team88.robot.commands.intake;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class IntakePneumaticsDown extends InstantCommand {

    public IntakePneumaticsDown() {
        super();
      
        requires(Robot.intake);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.intake.cradleDown();
    	
    }

}
