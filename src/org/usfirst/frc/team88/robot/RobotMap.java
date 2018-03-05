/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team88.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Drive - CAN
	public static final int driveLeftMaster = 6;
	public static final int[] driveLeftFollowers = { 4, 5, 7 };
	public static final int driveRightMaster = 10;
	public static final int[] driveRightFollowers = { 8, 9, 11};

	// Lift - CAN
	public static final int liftMaster = 12; 
	public static final int liftFollower = 13;

	// Intake - CAN
	public static final int intakeLeft = 3; 
	public static final int intakeRight = 2;
	// Intake - Analog inputs
	public static final int intakeLeftIR = 0;
	public static final int intakeRightIR = 3;
	// Intake - PCM
	public static final int intakeSolenoidIn = 1;
	public static final int intakeSolenoidOut = 0;
	

	// Other - unused Talons, CAN
	public static final int unused1 = 0;
	public static final int unused2 = 1; 
}
