package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.IntakePneumaticsDown;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.LiftSoftLanding;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideScale_Part1 extends CommandGroup {

    public AutoLeftSideScale_Part1() {
    	//Step 1: Score first Cube
		addSequential(new DriveZeroYaw());
		addParallel(new IntakePneumaticsDown());
		addParallel(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new AutoDriveDistanceAngleFast("LeftScaleDist", "LeftScaleAngle_1"));
		addSequential(new DriveRotateToAngle("LeftScaleAngle_2"));

		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));

		addParallel(new LiftSoftLanding());
		addSequential(new DriveRotateToAngle("LeftScaleAngle_3"));
		addSequential(new DriveRotateToAngle("LeftScaleAngle_3"));

    }
}
