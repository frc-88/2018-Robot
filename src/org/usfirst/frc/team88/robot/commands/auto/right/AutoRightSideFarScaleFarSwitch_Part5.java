package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScaleFarSwitch_Part5 extends CommandGroup {

    public AutoRightSideFarScaleFarSwitch_Part5() {
    	//Step 5: Prepare Second Cube
    	addParallel(new AutoDriveDistanceAngleFast("RightFarScaleFarSwitchDist_1", "RightFarScaleFarSwitchAngle_1"));
    	addSequential(new IntakeIntakeCube(4));
    	addSequential(new DriveRotateToAngle("RightFarScaleFarSwitchAngle_2"));
    	addSequential(new LiftGotoPosition(Lift.POS_SWITCH));
    	addSequential(new AutoDriveDistanceAngleFast("RightFarScaleFarSwitchDist_2", "RightFarScaleFarSwitchAngle_2"));
    	
    }
}
