package org.usfirst.frc.team88.robot.commands.lift;

import org.usfirst.frc.team88.robot.commands.dingles.DingleballOut;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LiftSwitchAndDingleOut extends CommandGroup {

	public LiftSwitchAndDingleOut() {
		addParallel(new LiftGotoPosition(Lift.POS_SWITCH));
		addParallel(new DingleballOut());
	}
}
