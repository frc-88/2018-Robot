package org.usfirst.frc.team88.robot.commands.drive;

import org.usfirst.frc.team88.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DriveZeroYaw extends InstantCommand {

    public DriveZeroYaw() {
        super();
        requires(Robot.drive);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.drive.zeroYaw();
    }

}
