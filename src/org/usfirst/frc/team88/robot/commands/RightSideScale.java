package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSideScale extends CommandGroup {

    public RightSideScale() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new DriveZeroYaw());
    	addParallel(new IntakePneumaticsDown());
    	addSequential(new AutoDriveDistanceAngle(21*12,0));
    	addSequential(new DriveRotateToAngle(-45));
    	addParallel(new LiftGotoPosition(Lift.POS_MID_SCALE));
    	//addSequential(new DriveRotateToAngle(-45));
    	//lift command here
    	//addSequential(new AutoDriveDistanceAngle(12,-45));
    	
    }
}
