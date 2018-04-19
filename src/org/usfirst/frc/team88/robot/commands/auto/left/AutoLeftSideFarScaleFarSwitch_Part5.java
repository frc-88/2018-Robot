package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngleFast;
import org.usfirst.frc.team88.robot.commands.drive.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.intake.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.commands.lift.LiftGotoPosition;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScaleFarSwitch_Part5 extends CommandGroup {

    public AutoLeftSideFarScaleFarSwitch_Part5() {
    	//Step 5: Prepare Second Cube
    	addParallel(new AutoDriveDistanceAngleFast("LeftFarScaleFarSwitchDist_1", "LeftFarScaleFarSwitchAngle_1"));
    	addSequential(new IntakeIntakeCube(4));
    	addSequential(new DriveRotateToAngle("LeftFarScaleFarSwitchAngle_2"));
    	addSequential(new LiftGotoPosition(Lift.POS_SWITCH));
    	addSequential(new AutoDriveDistanceAngleFast("LeftFarScaleFarSwitchDist_2", "LeftFarScaleFarSwitchAngle_2"));
    	
    }
}
