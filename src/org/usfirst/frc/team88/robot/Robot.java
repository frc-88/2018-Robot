/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team88.robot;

import org.usfirst.frc.team88.robot.commands.arm.ArmDownPart_1_v2;
import org.usfirst.frc.team88.robot.commands.auto.AutoCrossTheLine;
import org.usfirst.frc.team88.robot.commands.auto.AutoDriveDistance;
import org.usfirst.frc.team88.robot.commands.auto.center.AutoCenter;
import org.usfirst.frc.team88.robot.commands.auto.left.AutoLeft;
import org.usfirst.frc.team88.robot.commands.auto.left.AutoLeftOutOfWay;
import org.usfirst.frc.team88.robot.commands.auto.left.AutoLeftSideFarScale;
import org.usfirst.frc.team88.robot.commands.auto.left.AutoLeftSideScale;
import org.usfirst.frc.team88.robot.commands.auto.right.AutoRight;
import org.usfirst.frc.team88.robot.commands.auto.right.AutoRightSideFarScale;
import org.usfirst.frc.team88.robot.commands.auto.right.AutoRightSideScale;
import org.usfirst.frc.team88.robot.commands.dingles.DingleballTest;
import org.usfirst.frc.team88.robot.commands.drive.DriveResetEncoders;
import org.usfirst.frc.team88.robot.commands.drive.DriveRotateToAngle;
import org.usfirst.frc.team88.robot.commands.drive.DriveZeroYaw;
import org.usfirst.frc.team88.robot.commands.lift.LiftGotoPosition;
import org.usfirst.frc.team88.robot.subsystems.Arm;
import org.usfirst.frc.team88.robot.subsystems.Dingleball;
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
	public static Arm arm;
	public static Dingleball dingleball;
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
		drive = new Drive();
		intake = new Intake();
		lift = new Lift();
		arm = new Arm();
		dingleball = new Dingleball();
		
		oi = new OI();

		// Autonomous mode selector
		chooser.addDefault("Right", new AutoRight());
		chooser.addObject("Left", new AutoLeft());
		chooser.addObject("Left OOW", new AutoLeftOutOfWay());
		chooser.addObject("Center", new AutoCenter());
		chooser.addObject("Cross the Line", new AutoCrossTheLine());
		// testing modes below
		chooser.addObject("TEST:RNS", new AutoRightSideScale());
		chooser.addObject("TEST:RFS", new AutoRightSideFarScale());
		chooser.addObject("TEST:LNS", new AutoLeftSideScale());
		chooser.addObject("TEST:LFS", new AutoLeftSideFarScale());
		
		SmartDashboard.putData("Auto mode", chooser);

		// Autonomous mode buttons for testing
		SmartDashboard.putData("Auto Cross the Line", new AutoCrossTheLine());
		SmartDashboard.putData("Auto Left", new AutoLeft());
		SmartDashboard.putData("Auto Left OOW", new AutoLeftOutOfWay());
		SmartDashboard.putData("Auto Center", new AutoCenter());
		SmartDashboard.putData("Auto Right", new AutoRight());

		// buttons for calibration
		SmartDashboard.putData("Zero Yaw", new DriveZeroYaw());
		SmartDashboard.putData("Reset Encoders", new DriveResetEncoders());
		
		// Buttons to test commands and auto mode components
		SmartDashboard.putData("Drive 10 feet", new AutoDriveDistance(10 * 12));

		SmartDashboard.putData("Rotate to 0", new DriveRotateToAngle(0));
		SmartDashboard.putData("Rotate to 90", new DriveRotateToAngle(90));
		SmartDashboard.putData("Rotate to 180", new DriveRotateToAngle(180));
		SmartDashboard.putData("Rotate to -90", new DriveRotateToAngle(-90));
		
		SmartDashboard.putData("DingleballOut", new DingleballTest());
		SmartDashboard.putData("GOTO LIFT SWITCH", new LiftGotoPosition(Lift.POS_SWITCH));

		SmartDashboard.putData("TEST ARMDOWNv2", new ArmDownPart_1_v2());

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
		SmartDashboard.putString("Auto Command", chooser.getSelected().getName());

		// updateDashboard();
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
		Robot.intake.cradleDown();
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
		// Show subsystem commands
		SmartDashboard.putData("Drive", drive);
		SmartDashboard.putData("Intake", intake);
		SmartDashboard.putData("Lift", lift);
		SmartDashboard.putData("Dingle", dingleball);
		if (RobotMap.isTripleStack) {
			SmartDashboard.putData("Arm", arm);
		}

		drive.updateDashboard();
		intake.updateDashboard();
		lift.updateDashboard();
		dingleball.updateDashboard();
		if (RobotMap.isTripleStack) {
			arm.updateDashboard();
		}
	}
}
