package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.LiftSoftLanding;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistance;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScale_Part3 extends CommandGroup {

    public AutoLeftSideFarScale_Part3() {
    	//Step 3: Score First Cube
    	
    	addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE , .8));
		addSequential(new AutoDriveDistance(-36));
		addSequential(new LiftSoftLanding());
    }
}
