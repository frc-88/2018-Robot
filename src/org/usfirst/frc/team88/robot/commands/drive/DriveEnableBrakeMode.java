package org.usfirst.frc.team88.robot.commands.drive;

import org.usfirst.frc.team88.robot.Robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DriveEnableBrakeMode extends InstantCommand {

    public DriveEnableBrakeMode() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drive);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.drive.setNeutralMode(NeutralMode.Brake);
    }

}
