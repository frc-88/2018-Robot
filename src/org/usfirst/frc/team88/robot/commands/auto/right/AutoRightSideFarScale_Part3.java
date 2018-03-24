package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.LiftSoftLanding;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistance;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScale_Part3 extends CommandGroup {

    public AutoRightSideFarScale_Part3() {
    	//Step 3: Score First Cube
    	
    	addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE , .3));
		addSequential(new AutoDriveDistance(-36));
		addSequential(new LiftSoftLanding());
    }
}