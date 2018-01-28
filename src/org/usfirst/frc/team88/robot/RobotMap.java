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
	//Drive
	//TODO change numbers for new roboto.
	//public static final int[] rightMotorControl = {13, 9, 15, 10};
	//public static final int[] leftMototControl = {14, 11, 12, 8};
	
	public static final int leftTalonMaster = 14;
	public static final int[] leftVictors = {11, 12, 8};
	
	public static final int rightTalonMaster = 13;
	public static int[] rightVictors = {9,15,10};
	
	// Lift
	public static int liftTalon = 4;
	public static int rightwing = 6;

	
	// Intake
	public static int intakeLeftTalon = 5;
	public static int intakeRightTalon = 6;
	public static int intakeLeftIR = 0;	
	public static int intakeRightIR = 1;	
	
	// Other
	
	
	
}
