package org.usfirst.frc.team88.robot.commands.auto.left;

import org.usfirst.frc.team88.robot.commands.arm.ArmDown;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistanceAngle;
import org.usfirst.frc.team88.robot.commands.intake.IntakeEjectCube;
import org.usfirst.frc.team88.robot.commands.intake.IntakeIntakeCube;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideFarScale_Part3 extends CommandGroup {

    public AutoLeftSideFarScale_Part3() {
    	//Step 3: Score First Cube, go get the second
    	
		addSequential(new IntakeEjectCube(Lift.POS_HI_SCALE));
		addParallel(new ArmDown());
		addParallel(new AutoDriveDistanceAngle("LeftFarScaleDist_4", "LeftFarScaleAngle_4"));
		addSequential(new IntakeIntakeCube(4));
    }
}
