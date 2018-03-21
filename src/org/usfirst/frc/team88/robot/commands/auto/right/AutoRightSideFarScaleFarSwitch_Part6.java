package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.LiftSoftLanding;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistance;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideFarScaleFarSwitch_Part6 extends CommandGroup {

    public AutoRightSideFarScaleFarSwitch_Part6() {
    	//	Step 6: Score Second Cube in Switch
    	addSequential(new IntakeEjectCube(Lift.POS_SWITCH, 0.4));
    	addParallel(new AutoDriveDistance(-36));
    	addSequential(new LiftSoftLanding());
    }
}
