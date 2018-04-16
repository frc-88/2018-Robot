package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.ArmStart;
import org.usfirst.frc.team88.robot.commands.Delay;
import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale_Part3 extends CommandGroup {

    public AutoRightSideScale_Part3() {
    	// Step 2: Get in position to score second
		addSequential(new AutoDriveDistanceAngleFast("RightScaleDist_3","RightScaleAngle_3"));
		addSequential(new Delay(15));
		addSequential(new ArmStart());

    }
}
