package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.ArmDown;
import org.usfirst.frc.team88.robot.commands.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftOOWFar extends CommandGroup {

    public AutoLeftOOWFar() {
    	//Step 1: Drive L
    	
    	addSequential(new DriveZeroYaw());
		addSequential(new AutoDriveDistanceTurn(-162, -320, 99));
		addParallel(new ArmDown());
		
    }
}
