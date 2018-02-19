/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team88.robot;

import org.usfirst.frc.team88.robot.commands.AutoCenterToSwitch;
import org.usfirst.frc.team88.robot.commands.AutoCrossTheLine;
import org.usfirst.frc.team88.robot.commands.AutoDriveDistance;
import org.usfirst.frc.team88.robot.commands.AutoDriveDistanceAngle;
import org.usfirst.frc.team88.robot.commands.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.RightSideScale;
import org.usfirst.frc.team88.robot.commands.RightSideScaleThenSwitch;
import org.usfirst.frc.team88.robot.commands.RightSideSwitch;
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
		chooser.addDefault("Cross the Line", new AutoCrossTheLine());
		chooser.addObject("Center Switch", new AutoCenterToSwitch());
		SmartDashboard.putData("Auto mode", chooser);
		
		// Buttons to test commands
		SmartDashboard.putData("Command 100000", new AutoDriveDistance(100000));
		
		SmartDashboard.putData("Command Cross the Line", new AutoCrossTheLine());
		SmartDashboard.putData("Command Center Switch", new AutoCenterToSwitch());
		SmartDashboard.putData("Command RightSwitch", new RightSideSwitch());
		SmartDashboard.putData("Command RightScale", new RightSideScale());
		
		SmartDashboard.putData("Command RightScaleThenSwitch", new RightSideScaleThenSwitch());
		
		SmartDashboard.putData("Command Zero Yaw", new DriveZeroYaw());
		
		
		SmartDashboard.putData("Command Rotate to 0", new DriveRotateToAngle(0));
		SmartDashboard.putData("Command Rotate to 90", new DriveRotateToAngle(90));
		SmartDashboard.putData("Command Rotate to 180", new DriveRotateToAngle(180));
		SmartDashboard.putData("Command Rotate to -90", new DriveRotateToAngle(-90));
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
		SmartDashboard.putString("Robot/Auto Command", chooser.getName());

		// Show subsystem commands
		SmartDashboard.putData("Robot Drive", drive);
		SmartDashboard.putData("Robot Intake", intake);
		SmartDashboard.putData("Robot Lift", lift);

		drive.updateDashboard();
		intake.updateDashboard();
		lift.updateDashboard();
	}
}
