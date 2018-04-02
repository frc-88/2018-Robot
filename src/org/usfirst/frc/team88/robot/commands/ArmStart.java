package org.usfirst.frc.team88.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ArmStart extends CommandGroup {

    public ArmStart() {
        addSequential(new ArmStartPart_1());
        addSequential(new LiftSoftLanding());
    }
}
