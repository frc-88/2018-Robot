package org.usfirst.frc.team88.robot.commands.arm;

import org.usfirst.frc.team88.robot.commands.dingles.DingleballIn;
import org.usfirst.frc.team88.robot.commands.lift.LiftSoftLanding;

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
