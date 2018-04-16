package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.ArmDown;
import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.LiftSoftLanding;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale_Part4 extends CommandGroup {

    public AutoRightSideScale_Part4() {
		// Step 3: Score second
		
		//addParallel(new DriveRotateToAngle("RightScaleAngle_4"));
		addParallel(new LiftGotoPosition(Lift.POS_HI_SCALE));
		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));
		addParallel(new ArmDown());

    }
}
