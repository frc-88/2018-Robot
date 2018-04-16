package org.usfirst.frc.team88.robot.commands.auto.center;

import org.usfirst.frc.team88.robot.commands.DingleballOut;
import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.PowerUpConditionalCommand;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistance;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter_Part35 extends CommandGroup {

	public AutoCenter_Part35() {
		// back off and turn towards our scale
		addParallel(new DingleballOut());
		addParallel(new LiftGotoPosition(Lift.POS_SWITCH));
		addSequential(new PowerUpConditionalCommand(new AutoDriveDistanceAngleFast(-50,50),
				new AutoDriveDistanceAngleFast(-50,50),
				new AutoDriveDistanceAngleFast(-50,-50),
				new AutoDriveDistanceAngleFast(-50,-50)));
		
		addSequential(new AutoDriveDistanceAngleFast(55, 0));
		addSequential(new IntakeEjectCube(Lift.POS_SWITCH));
	}
}
