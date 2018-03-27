package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.LiftSoftLanding;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideScale_Part3 extends CommandGroup {

    public AutoRightSideScale_Part3() {
    	//Step 3: Score second cube
    	addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));
		
		addParallel(new LiftSoftLanding());
		addSequential(new DriveRotateToAngle(0));
		addSequential(new DriveRotateToAngle(0));

    }
}
