package org.usfirst.frc.team88.robot.commands.auto.center;

import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.LiftSoftLanding;
import org.usfirst.frc.team88.robot.commands.PowerUpConditionalCommand;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenter_Part1 extends CommandGroup {

	public AutoCenter_Part1() {
		// preloaded cube into the correct switch
		addSequential(new AutoCenterToSwitch());

		// back off and soft land
		addSequential(new AutoDriveDistance(-60));
		addParallel(new LiftSoftLanding());

		// turn towards a second cube
		addSequential(new PowerUpConditionalCommand(new DriveRotateToAngle("CenterLeftAngle"),
				new DriveRotateToAngle("CenterLeftAngle"), 
				new DriveRotateToAngle("CenterRightAngle"),
				new DriveRotateToAngle("CenterRightAngle")));
		addSequential(new PowerUpConditionalCommand(new DriveRotateToAngle("CenterLeftAngle"),
				new DriveRotateToAngle("CenterLeftAngle"), 
				new DriveRotateToAngle("CenterRightAngle"),
				new DriveRotateToAngle("CenterRightAngle")));
	}
}
