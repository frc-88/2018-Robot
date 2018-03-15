package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveToNullAndLift extends CommandGroup {

    public AutoDriveToNullAndLift() {
    	
    	addSequential(new DriveZeroYaw());
    	addSequential(new AutoDriveDistanceAngle(22 * 12, 0));
    	addSequential(new LiftGotoPosition(Lift.POS_MID_SCALE));
    	
    }
}
