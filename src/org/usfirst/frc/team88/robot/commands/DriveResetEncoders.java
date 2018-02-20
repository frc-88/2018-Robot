package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DriveResetEncoders extends InstantCommand {

    public DriveResetEncoders() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drive);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.drive.resetEncoders();
    }

}
