package org.usfirst.frc.team88.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ArmDown extends CommandGroup {

    public ArmDown() {
    	addSequential(new ArmDownPart_1());
    	addSequential(new Delay(5));
    	addSequential(new LiftSoftLanding());
    }
}
