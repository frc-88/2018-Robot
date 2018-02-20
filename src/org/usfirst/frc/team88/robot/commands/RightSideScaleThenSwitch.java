package org.usfirst.frc.team88.robot.commands;

import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSideScaleThenSwitch extends CommandGroup {

    public RightSideScaleThenSwitch() {
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
    	addSequential(new DriveEnableBreakMode());
    	addParallel(new IntakePneumaticsDown());
    	addSequential(new AutoDriveDistanceAngleFast(25.25*12,0));
    	addSequential(new DriveRotateToAngle(-45));
//    	addSequential(new AutoDriveDistanceAngle(2, -45));
    	addSequential(new LiftGotoPosition(Lift.POS_HI_SCALE));
    	addSequential(new LiftCheckOnTarget(Lift.POS_HI_SCALE));
    	addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));
    	
    	addParallel(new LiftSoftLanding());
    	addSequential(new AutoDriveDistance(-10));
    	
    	
    	addSequential(new DriveRotateToAngle(-125));
    	addParallel(new AutoDriveDistanceAngle(64, -147));
    	addSequential(new IntakeIntakeCube(5));
    	addParallel(new LiftGotoPosition(Lift.POS_SWITCH));
    	addSequential(new AutoDriveDistanceAngle(12, -147));
    	addSequential(new IntakeEjectCube(Lift.POS_SWITCH));
//    	addSequential(new AutoDriveDistance(-20));
//    	addParallel(new LiftSoftLanding());
//    	addSequential(new DriveRotateToAngle(0));
    	
    	addSequential(new DriveDisableBreakMode());
    }
    
}
