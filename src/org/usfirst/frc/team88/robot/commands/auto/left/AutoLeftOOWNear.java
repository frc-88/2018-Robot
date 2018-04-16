package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.ArmDown;
import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.HaveCubeConditionalCommand;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.StepConditionalCommand;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftOOWNear extends CommandGroup {

	public AutoLeftOOWNear() {
		addSequential(new DriveZeroYaw());
		addSequential(new AutoDriveDistanceAngleFast(-300, -1));
		addParallel(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new DriveRotateToAngle(70));
		addSequential(new DriveRotateToAngle(70));
		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));
		addParallel(new ArmDown());
		//addSequential(new AutoDriveDistanceAngleFast(10, 180));
	}
}
