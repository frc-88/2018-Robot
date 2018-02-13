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
	public static final int driveLeftMaster = 10;
	public static final int[] driveLeftFollowers = { 8, 9, 11 };
	public static final int driveRightMaster = 6;
	public static final int[] driveRightFollowers = { 4, 5, 7};

	// Lift - CAN
	public static final int liftMaster = 13; 
	public static final int liftFollower = 12;

	// Intake - CAN
	public static final int intakeLeft = 3; 
	public static final int intakeRight = 2;
	// Intake - Analog inputs
	public static final int intakeLeftIR = 3;
	public static final int intakeRightIR = 0;
	// Intake - PCM
	public static final int intakeSolenoidIn = 0;
	public static final int intakeSolenoidOut = 1;
	

	// Other - unused Talons, CAN
	public static final int unused1 = 0;
	public static final int unused2 = 1; 
}
