/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team88.robot;

import org.usfirst.frc.team88.robot.commands.AutoCenter;
import org.usfirst.frc.team88.robot.commands.AutoCrossTheLine;
import org.usfirst.frc.team88.robot.commands.AutoDriveDistance;
import org.usfirst.frc.team88.robot.commands.AutoLeft;
import org.usfirst.frc.team88.robot.commands.AutoLeftSideL;
import org.usfirst.frc.team88.robot.commands.AutoLeftSideScale;
import org.usfirst.frc.team88.robot.commands.AutoLeftSideScaleThenSwitch;
import org.usfirst.frc.team88.robot.commands.AutoLeftSideSwitch;
import org.usfirst.frc.team88.robot.commands.AutoRight;
import org.usfirst.frc.team88.robot.commands.AutoRightSideL;
import org.usfirst.frc.team88.robot.commands.DriveResetEncoders;
import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.LiftCalibrate;
import org.usfirst.frc.team88.robot.commands.AutoRightSideScale;
import org.usfirst.frc.team88.robot.commands.AutoRightSideScaleThenSwitch;
import org.usfirst.frc.team88.robot.commands.AutoRightSideSwitch;
import org.usfirst.frc.team88.robot.subsystems.Drive;
import org.usfirst.frc.team88.robot.subsystems.Intake;
import org.usfirst.frc.team88.robot.subsystems.Lift;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static Drive drive;
	public static Intake intake;
	public static Lift lift;
	public static OI oi;

	private Command autonomousCommand;
	private SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
		intake = new Intake();
		lift = new Lift();
		drive = new Drive();

		oi = new OI();

		// Autonomous mode selector
		chooser.addDefault("Center", new AutoCenter());
		chooser.addObject("Left", new AutoLeft());
		chooser.addObject("Right", new AutoRight());
		chooser.addObject("Cross the Line", new AutoCrossTheLine());
		SmartDashboard.putData("Auto mode", chooser);

		// Autonomous mode buttons for testing
		SmartDashboard.putData("Auto Cross the Line", new AutoCrossTheLine());
		SmartDashboard.putData("Auto Left", new AutoLeft());
		SmartDashboard.putData("Auto Center", new AutoCenter());
		SmartDashboard.putData("Auto Right", new AutoRight());

		// buttons for calibration
		SmartDashboard.putData("Zero Yaw", new DriveZeroYaw());
		SmartDashboard.putData("Reset Encoders", new DriveResetEncoders());
		SmartDashboard.putData("Calibrate Lift", new LiftCalibrate());
		
		// Buttons to test commands and auto mode components
		SmartDashboard.putData("Drive 10 feet", new AutoDriveDistance(10 * 12));

		SmartDashboard.putData("Rotate to 0", new DriveRotateToAngle(0));
		SmartDashboard.putData("Rotate to 90", new DriveRotateToAngle(90));
		SmartDashboard.putData("Rotate to 180", new DriveRotateToAngle(180));
		SmartDashboard.putData("Rotate to -90", new DriveRotateToAngle(-90));

		SmartDashboard.putData("Left Switch", new AutoLeftSideSwitch());
		SmartDashboard.putData("Left Scale", new AutoLeftSideScale());
		SmartDashboard.putData("Left ScaleThenSwitch", new AutoLeftSideScaleThenSwitch());
		SmartDashboard.putData("Left Park", new AutoLeftSideL());

		SmartDashboard.putData("Right Switch", new AutoRightSideSwitch());
		SmartDashboard.putData("Right Scale", new AutoRightSideScale());
		SmartDashboard.putData("Right ScaleThenSwitch", new AutoRightSideScaleThenSwitch());
		SmartDashboard.putData("Right Park", new AutoRightSideL());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

		updateDashboard();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		updateDashboard();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		Robot.drive.setNeutralMode(NeutralMode.Coast);
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		updateDashboard();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	private void updateDashboard() {
		SmartDashboard.putString("Auto Command", chooser.getName());

		// Show subsystem commands
		SmartDashboard.putData("Robot Drive", drive);
		SmartDashboard.putData("Robot Intake", intake);
		SmartDashboard.putData("Robot Lift", lift);

		drive.updateDashboard();
		intake.updateDashboard();
		lift.updateDashboard();
	}
}
