package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.arm.ArmDown;
import org.usfirst.frc.team88.robot.commands.intake.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.lift.LiftGotoPosition;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale_Part4 extends CommandGroup {

    public AutoRightSideScale_Part4() {
		// Step 3: Score second
		
		//addParallel(new DriveRotateToAngle("RightScaleAngle_4"));
		addParallel(new LiftGotoPosition(Lift.POS_MID_SCALE));
		addSequential(new IntakeEjectCube(Lift.POS_MID_SCALE));
		addParallel(new ArmDown());

    }
}
