/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team88.robot;

import org.usfirst.frc.team88.robot.commands.LiftGotoPosition;
import org.usfirst.frc.team88.robot.commands.LiftSoftLanding;
import org.usfirst.frc.team88.robot.subsystems.Lift;
import org.usfirst.frc.team88.robot.commands.IntakePneumaticsDown;
import org.usfirst.frc.team88.robot.commands.IntakePneumaticsUp;
import org.usfirst.frc.team88.robot.util.TJController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final TJController driver;
	public final TJController operator;

	public OI() {
		driver = new TJController(0);
		operator = new TJController(1);

		// !!! DO NOT ADD COMMANDS TO DRIVER BUTTONS !!!
		// The A, B, X, and Y buttons on the driver controlled are used in the
		// DriveSplitArcade command to provide field oriented options. Commands
		// should not be assigned to them.
		
//		operator.buttonRightBumper.whenPressed(new IntakePneumaticsUp());
//		operator.buttonLeftBumper.whenPressed(new IntakePneumaticsDown());
		
		operator.buttonB.whenPressed(new LiftGotoPosition(Lift.POS_SWITCH));
		operator.buttonB.whenReleased(new LiftSoftLanding());
		
		operator.buttonA.whenPressed(new LiftGotoPosition(Lift.POS_LOW_SCALE));
		operator.buttonA.whenReleased(new LiftSoftLanding());
		
		operator.buttonX.whenPressed(new LiftGotoPosition(Lift.POS_MID_SCALE));
		operator.buttonX.whenReleased(new LiftSoftLanding());
		
		operator.buttonY.whenPressed(new LiftGotoPosition(Lift.POS_HI_SCALE));
		operator.buttonY.whenReleased(new LiftSoftLanding());
	}
	
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
