package org.usfirst.frc.team88.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartingConfiguration extends CommandGroup {

    public StartingConfiguration() {
    	addSequential(new DingleballIn());
    	addSequential(new ArmStart());
        addSequential(new LiftSoftLanding());
    }
}
