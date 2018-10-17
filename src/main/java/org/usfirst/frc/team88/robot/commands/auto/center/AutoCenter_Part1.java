package org.usfirst.frc.team88.robot.commands.auto.center;

import org.usfirst.frc.team88.robot.commands.PowerUpConditionalCommand;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.commands.dingles.DingleballIn;
import org.usfirst.frc.team88.robot.commands.drive.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.lift.LiftSoftLanding;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter_Part1 extends CommandGroup {

	public AutoCenter_Part1() {
		// preloaded cube into the correct switch
		addSequential(new AutoCenterToSwitch());

		// back off and soft land
		addSequential(new AutoDriveDistanceAngleFast(-40, 0));
		addParallel(new LiftSoftLanding());
		addParallel(new DingleballIn());

		// turn towards a second cube
		addSequential(new PowerUpConditionalCommand(new DriveRotateToAngle("CenterLeftAngle"),
				new DriveRotateToAngle("CenterLeftAngle"), 
				new DriveRotateToAngle("CenterRightAngle"),
				new DriveRotateToAngle("CenterRightAngle")));
	}
}
