/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team88.robot;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final boolean isTripleStack = false;

	// Drive - CAN
	public static final int driveLeftMaster = isTripleStack ? 6 : 10;
	public static final int[] driveLeftFollowers = isTripleStack ? new int[] { 4, 5, 7 } : new int[] { 8, 9, 11 };
	public static final int driveRightMaster = isTripleStack ? 10 : 6;
	public static final int[] driveRightFollowers = isTripleStack ? new int[] { 8, 9, 11 } : new int[] { 4, 5, 7 };

	// Lift - CAN
	public static final int liftMaster = isTripleStack ? 12 : 13;
	public static final int liftFollower = isTripleStack ? 13 : 12;
	public static final int liftFollower2 = 1;

	// Intake - CAN
	public static final int intakeLeft = 3;
	public static final int intakeRight = 2;
	// Intake - Analog inputs
	public static final int intakeLeftIR = 0;
	public static final int intakeRightIR = 3;
	// Intake - PCM
	public static final int intakeSolenoidIn = isTripleStack ? 1 : 0;
	public static final int intakeSolenoidOut = isTripleStack ? 0 : 1;

	// Arm - CAN
	public static final int armMotor = 0;
	
	// Dingleballs - PWM
	public static final int leftDingle = 8;
	public static final int rightDingle = 9;
}
