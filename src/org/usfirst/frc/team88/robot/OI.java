/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team88.robot;

import org.usfirst.frc.team88.robot.commands.LiftGotoBottom;
import org.usfirst.frc.team88.robot.commands.LiftGotoHighScale;
import org.usfirst.frc.team88.robot.commands.LiftGotoLowScale;
import org.usfirst.frc.team88.robot.commands.LiftGotoMidScale;
import org.usfirst.frc.team88.robot.commands.LiftGotoSwitch;
import org.usfirst.frc.team88.robot.commands.intakePneumaticsDown;
import org.usfirst.frc.team88.robot.commands.intakePneumaticsUp;
import org.usfirst.frc.team88.robot.util.TJController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public TJController driver = new TJController(0);
	public TJController operator = new TJController(1);

	// !!! DO NOT ADD COMMANDS TO DRIVER BUTTONS !!!
	// The A, B, X, and Y buttons on the driver controlled are used in the
	// DriveSplitArcade command to provide field oriented options. Commands
	// should not be assigned to them.
	
	public OI() {
		operator.buttonRightBumper.whenPressed(new intakePneumaticsUp());
		operator.buttonLeftBumper.whenPressed(new intakePneumaticsDown());
		
		operator.buttonB.whenPressed(new LiftGotoSwitch());
		operator.buttonB.whenReleased(new LiftGotoBottom());
		
		operator.buttonA.whenPressed(new LiftGotoLowScale());
		operator.buttonA.whenReleased(new LiftGotoBottom());
		
		operator.buttonX.whenPressed(new LiftGotoMidScale());
		operator.buttonX.whenReleased(new LiftGotoBottom());
		
		operator.buttonY.whenPressed(new LiftGotoHighScale());
		operator.buttonY.whenReleased(new LiftGotoBottom());
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
