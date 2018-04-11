package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.RobotMap;
import org.usfirst.frc.team88.robot.commands.DriveSplitArcade;
import org.usfirst.frc.team88.robot.commands.DriveTank;
import org.usfirst.frc.team88.robot.util.PIDHeadingCorrection;
import org.usfirst.frc.team88.robot.util.TJUtility;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * <pre>
 * Brodie B., Sam R.
 * 
 * Took an hour to write
 * Fixed it all with three letters
 * Had to change it back.
 * </pre>
 */
public class Drive extends Subsystem implements PIDOutput {

	private final static boolean CLOSED_LOOP = true;
	private final static boolean SPLIT_ARCADE = true;

	private final static int SLOTIDX = 0;
	private final static int TIMEOUTMS = 0;
	private final static double RAMPRATE = .30;
	private final static double MAX_RAMPRATE = 1.5;
	private final static double MIN_RAMPRATE = .50;
	private final static double MAX_SPEED = 13000;
	private final static double P = 0.04;
	private final static double I = 0.0;
	private final static double D = 0.0;
	private final static double F = (1023 / MAX_SPEED) * 0.8;
	private final static double DFT_SENSITIVITY = 0.15;

	private final static double ROTATE_P = 0.0060;
	private final static double ROTATE_I = 0.0004;
	private final static double ROTATE_D = 0.0;
	private final static double ROTATE_F = 0.0;
	private final static double ROTATE_TOLERANCE = 3.0;
	private final static double ROTATE_MAX = 0.30;
	private final static double ROTATE_MIN = 0.05;

	private final static double HEADING_P = 0.008;
	private final static double HEADING_I = 0.0;
	private final static double HEADING_D = 0.0;
	private final static double HEADING_F = 0.0;
	private final static double HEADING_TOLERANCE = 0.5;
	private final static double OFFANGLETHRESHOLDDEGREES = 20;
	private final static double ONANGLETHRESHOLDDEGREES = 15; 
	private AHRS navX;
	
	private double lastAccelX = 0;
	private double lastAccelY = 0;
	
	private TalonSRX leftMaster;
	private TalonSRX rightMaster;
	private TalonSRX[] leftFollower;
	private TalonSRX[] rightFollower;

	private int headingCount;
	private int ramprateCount;
	private double ramprate;
	private boolean autoBalanceXMode;
	
	public PIDController rotateController;
	private PIDController headingController;
	private PIDHeadingCorrection headingCorrection;

	public Drive() {
		// init navX
		navX = new AHRS(SPI.Port.kMXP);

		// init rotateController
		rotateController = new PIDController(ROTATE_P, ROTATE_I, ROTATE_D, ROTATE_F, navX, this);
		rotateController.setInputRange(-180.0f, 180.0f);
		rotateController.setOutputRange(-1.0, 1.0);
		rotateController.setAbsoluteTolerance(ROTATE_TOLERANCE);
		rotateController.setContinuous(true);

		// init headingController
		headingCorrection = new PIDHeadingCorrection();

		headingController = new PIDController(HEADING_P, HEADING_I, HEADING_D, HEADING_F, navX, headingCorrection);
		headingController.setInputRange(-180.0f, 180.0f);
		headingController.setOutputRange(-1.0, 1.0);
		headingController.setAbsoluteTolerance(HEADING_TOLERANCE);
		headingController.setContinuous(true);

		headingController.reset();
		headingController.disable();

		// init motor controllers
		leftMaster = new TalonSRX(RobotMap.driveLeftMaster);
		rightMaster = new TalonSRX(RobotMap.driveRightMaster);

		leftFollower = new TalonSRX[RobotMap.driveLeftFollowers.length];
		rightFollower = new TalonSRX[RobotMap.driveRightFollowers.length];
		for (int i = 0; i < RobotMap.driveLeftFollowers.length; i++) {
			leftFollower[i] = new TalonSRX(RobotMap.driveLeftFollowers[i]);
		}
		for (int i = 0; i < RobotMap.driveRightFollowers.length; i++) {
			rightFollower[i] = new TalonSRX(RobotMap.driveRightFollowers[i]);
		}

		// configure motor controllers
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, SLOTIDX, TIMEOUTMS);
		leftMaster.config_kP(SLOTIDX, P, TIMEOUTMS);
		leftMaster.config_kI(SLOTIDX, I, TIMEOUTMS);
		leftMaster.config_kD(SLOTIDX, D, TIMEOUTMS);
		leftMaster.config_kF(SLOTIDX, F, TIMEOUTMS);
		leftMaster.configNominalOutputForward(0.0, TIMEOUTMS);
		leftMaster.configNominalOutputReverse(0.0, TIMEOUTMS);
		leftMaster.configPeakOutputForward(+0.83, TIMEOUTMS);
		leftMaster.configPeakOutputReverse(-0.83, TIMEOUTMS);
		leftMaster.configNeutralDeadband(0.04, TIMEOUTMS);
		leftMaster.configOpenloopRamp(RAMPRATE, TIMEOUTMS);
		leftMaster.configClosedloopRamp(RAMPRATE, TIMEOUTMS);
		leftMaster.setSensorPhase(false);
		leftMaster.setNeutralMode(NeutralMode.Coast);

		for (int i = 0; i < RobotMap.driveLeftFollowers.length; i++) {
			leftFollower[i].setNeutralMode(NeutralMode.Coast);
			leftFollower[i].follow(leftMaster);
		}

		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, SLOTIDX, TIMEOUTMS);
		rightMaster.config_kP(SLOTIDX, P, TIMEOUTMS);
		rightMaster.config_kI(SLOTIDX, I, TIMEOUTMS);
		rightMaster.config_kD(SLOTIDX, D, TIMEOUTMS);
		rightMaster.config_kF(SLOTIDX, F, TIMEOUTMS);
		rightMaster.configNominalOutputForward(0.0, TIMEOUTMS);
		rightMaster.configNominalOutputReverse(0.0, TIMEOUTMS);
		rightMaster.configPeakOutputForward(+0.83, TIMEOUTMS);
		rightMaster.configPeakOutputReverse(-0.83, TIMEOUTMS);
		rightMaster.configNeutralDeadband(0.04, TIMEOUTMS);
		rightMaster.configOpenloopRamp(RAMPRATE, TIMEOUTMS);
		rightMaster.configClosedloopRamp(RAMPRATE, TIMEOUTMS);
		rightMaster.setSensorPhase(false);
		rightMaster.setNeutralMode(NeutralMode.Coast);

		for (int i = 0; i < RobotMap.driveRightFollowers.length; i++) {
			rightFollower[i].setNeutralMode(NeutralMode.Coast);
			rightFollower[i].follow(rightMaster);
		}

		resetEncoders();
		navX.zeroYaw();
		headingCount = 0;
		ramprateCount = 0;
		autoBalanceXMode = false;

	}

	public void wheelSpeed(double left, double right) {

		ramprate = (MAX_RAMPRATE - MIN_RAMPRATE) * Robot.lift.getPercentHeight() + MIN_RAMPRATE;

		if (ramprateCount++ > 10) {
			ramprateCount = 0;
			leftMaster.configOpenloopRamp(ramprate,TIMEOUTMS);
			leftMaster.configClosedloopRamp(ramprate,TIMEOUTMS);
			rightMaster.configOpenloopRamp(ramprate, TIMEOUTMS);
			rightMaster.configClosedloopRamp(ramprate, TIMEOUTMS);
		}
		double pitchAngleDegrees = navX.getPitch();
		if ( !autoBalanceXMode && 
				(Math.abs(pitchAngleDegrees) >= 
				Math.abs(OFFANGLETHRESHOLDDEGREES))) {
			autoBalanceXMode = true;
		}else if ( autoBalanceXMode && 
				(Math.abs(pitchAngleDegrees) <= 
				Math.abs(ONANGLETHRESHOLDDEGREES))) {
			autoBalanceXMode = false;
		}

		if(autoBalanceXMode){

			double pitchAngleRadians = pitchAngleDegrees * (Math.PI / 180.0);
			right = left = Math.sin(pitchAngleRadians) * -1;
			
			SmartDashboard.putNumber("Drive Left Input", left);
			SmartDashboard.putNumber("Drive Right Input", right);
			
			leftMaster.set(ControlMode.PercentOutput, -left);
			rightMaster.set(ControlMode.PercentOutput, right);
			
		}else if (CLOSED_LOOP) {
			SmartDashboard.putNumber("Drive Left Input", -left * MAX_SPEED);
			SmartDashboard.putNumber("Drive Right Input", right * MAX_SPEED);

			leftMaster.set(ControlMode.Velocity, -left * MAX_SPEED);
			rightMaster.set(ControlMode.Velocity, right * MAX_SPEED);
		} else {
			SmartDashboard.putNumber("Drive Left Input", left);
			SmartDashboard.putNumber("Drive Right Input", right);

			leftMaster.set(ControlMode.PercentOutput, -left);
			rightMaster.set(ControlMode.PercentOutput, right);
		}

	}

	/**
	 * The below was based on (ie, copied from) very similar code in the WPILib
	 * RobotDrive class on 1/18/2017
	 * 
	 * Drive the motors at "outputMagnitude" and "curve". Both outputMagnitude and
	 * curve are -1.0 to +1.0 values, where 0.0 represents stopped and not turning.
	 * {@literal curve < 0 will turn left
	 * and curve > 0} will turn right.
	 *
	 * <p>
	 * The algorithm for steering provides a constant turn radius for any normal
	 * speed range, both forward and backward. Increasing sensitivity causes sharper
	 * turns for fixed values of curve.
	 *
	 * <p>
	 * This function will most likely be used in an autonomous routine.
	 *
	 * @param outputMagnitude
	 *            The speed setting for the outside wheel in a turn, forward or
	 *            backwards, +1 to -1.
	 * @param curve
	 *            The rate of turn, constant for different forward speeds. Set
	 *            {@literal
	 *                        curve < 0 for left turn or curve > 0 for right turn.}
	 *            Set curve = e^(-r/w) to get a turn radius r for wheelbase w of
	 *            your robot. Conversely, turn radius r = -ln(curve)*w for a given
	 *            value of curve and wheelbase w.
	 */
	public void driveCurve(double outputMagnitude, double curve) {
		driveCurve(outputMagnitude, curve, DFT_SENSITIVITY);
	}

	public void driveCurve(double outputMagnitude, double curve, double sensitivity) {
		double leftOutput;
		double rightOutput;

		SmartDashboard.putNumber("Drive Curve", curve);
		SmartDashboard.putNumber("Drive Magnitude", outputMagnitude);
		SmartDashboard.putNumber("Drive Count", headingCount);
		SmartDashboard.putBoolean("Drive Stabilize", headingController.isEnabled());

		if (outputMagnitude == 0) {
			headingController.disable();
			headingCount = 0;
		} else if (headingController.isEnabled() && curve == 0) {
			curve = headingCorrection.getHeadingCorrection();
		} else if (headingController.isEnabled()) {
			headingController.disable();
			headingCount = 0;
		}

		if (outputMagnitude != 0) {
			curve = curve * Math.signum(outputMagnitude);
		}

		curve = TJUtility.maxValue(curve, 1);
		
		if ((outputMagnitude == 0) && (Math.abs(getAvgVelocity()) < 0.3 * MAX_SPEED)) {
			leftOutput = curve * 0.5;
			rightOutput = -curve * 0.5;
		} else if (curve < 0) {
			double value = Math.log(-curve);
			double ratio = (value - sensitivity) / (value + sensitivity);
			if (ratio == 0) {
				ratio = .0000000001;
			}
			leftOutput = outputMagnitude / ratio;
			rightOutput = outputMagnitude;
		} else if (curve > 0) {
			double value = Math.log(curve);
			double ratio = (value - sensitivity) / (value + sensitivity);
			if (ratio == 0) {
				ratio = .0000000001;
			}
			leftOutput = outputMagnitude;
			rightOutput = outputMagnitude / ratio;
		} else {
			if (headingCount++ > 4) {
				headingController.reset();
				headingController.enable();
				headingController.setSetpoint(getYaw());
			}
			leftOutput = outputMagnitude;
			rightOutput = outputMagnitude;
		}

		wheelSpeed(leftOutput, rightOutput);
	}

	public void setNeutralMode(NeutralMode mode) {
		leftMaster.setNeutralMode(mode);
		for (int i = 0; i < RobotMap.driveLeftFollowers.length; i++) {
			leftFollower[i].setNeutralMode(mode);
		}

		rightMaster.setNeutralMode(mode);
		for (int i = 0; i < RobotMap.driveRightFollowers.length; i++) {
			rightFollower[i].setNeutralMode(mode);
		}

	}

	public double getYaw() {
		return navX.getYaw();
	}

	public void zeroYaw() {
		navX.zeroYaw();
	}

	public void resetEncoders() {
		leftMaster.getSensorCollection().setQuadraturePosition(0, TIMEOUTMS);
		rightMaster.getSensorCollection().setQuadraturePosition(0, TIMEOUTMS);
	}

	public void enableRampRate() {
		leftMaster.configClosedloopRamp(RAMPRATE, TIMEOUTMS);
		rightMaster.configClosedloopRamp(RAMPRATE, TIMEOUTMS);
	}

	public void disableRampRate() {
		leftMaster.configClosedloopRamp(0, TIMEOUTMS);
		rightMaster.configClosedloopRamp(0, TIMEOUTMS);
	}

	public int getLeftPosition() {
		return leftMaster.getSelectedSensorPosition(SLOTIDX);
	}

	public int getRightPosition() {
		return rightMaster.getSelectedSensorPosition(SLOTIDX);
	}

	public double getAvgPosition() {
		return (-leftMaster.getSelectedSensorPosition(SLOTIDX) + rightMaster.getSelectedSensorPosition(SLOTIDX))
				/ 2.0;
	}

	public double getAvgVelocity() {
		double speed = (-leftMaster.getSelectedSensorVelocity(SLOTIDX)
				+ rightMaster.getSelectedSensorVelocity(SLOTIDX)) / 2;

		return speed;
	}
	
	public void enableTURBOMODE(){
		leftMaster.configPeakOutputForward(1, TIMEOUTMS);
		leftMaster.configPeakOutputReverse(-1, TIMEOUTMS);
		rightMaster.configPeakOutputForward(1, TIMEOUTMS);
		rightMaster.configPeakOutputReverse(-1, TIMEOUTMS);
	}
	
	public void disableTURBOMODE(){
		leftMaster.configPeakOutputForward(+0.83, TIMEOUTMS);
		leftMaster.configPeakOutputReverse(-0.83, TIMEOUTMS);
		rightMaster.configPeakOutputForward(+0.83, TIMEOUTMS);
		rightMaster.configPeakOutputReverse(-0.83, TIMEOUTMS);
	}
	
	public double getJerkX(){
		return lastAccelX-navX.getWorldLinearAccelX();
	}
	
	public double getJerkY(){
		return lastAccelY-navX.getWorldLinearAccelY();
	}
	
	public void resetDisplacement() {
		navX.resetDisplacement();
	}
	
	public double getDisplacementX(){
		return navX.getDisplacementX();
	}
	
	public double getDisplacementY(){
		return navX.getDisplacementY();
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber("Drive AvgPosition", getAvgPosition());
		SmartDashboard.putNumber("Drive AvgVelocity", getAvgVelocity());
		SmartDashboard.putNumber("Drive Yaw", navX.getYaw());
		SmartDashboard.putNumber("Drive Pitch", navX.getPitch());
		SmartDashboard.putNumber("Drive Roll", navX.getRoll());
		SmartDashboard.putNumber("Drive Ramprate", ramprate);
		SmartDashboard.putNumber("Drive Acceleration X", navX.getWorldLinearAccelX());
		SmartDashboard.putNumber("Drive Acceleration Y", navX.getWorldLinearAccelY());
		
		SmartDashboard.putNumber("Drive Jerk X", lastAccelX - navX.getWorldLinearAccelX());
		SmartDashboard.putNumber("Drive Jerk Y", lastAccelY - navX.getWorldLinearAccelY());
		lastAccelX = navX.getWorldLinearAccelX();
		lastAccelY = navX.getWorldLinearAccelY();
		
		SmartDashboard.putNumber("Drive Displacement X", navX.getDisplacementX());
		SmartDashboard.putNumber("Drive Displacement Y", navX.getDisplacementY());
		
		SmartDashboard.putNumber("Drive Left Master Position", leftMaster.getSelectedSensorPosition(SLOTIDX));
		SmartDashboard.putNumber("Drive Left Master Velocity", leftMaster.getSelectedSensorVelocity(SLOTIDX));
		SmartDashboard.putNumber("Drive Left Master Error", leftMaster.getClosedLoopError(SLOTIDX));
		SmartDashboard.putNumber("Drive Left Master Current", leftMaster.getOutputCurrent());
		SmartDashboard.putNumber("Drive Left Master Voltage", leftMaster.getMotorOutputVoltage());

		SmartDashboard.putNumber("Drive Right Master Position", rightMaster.getSelectedSensorPosition(SLOTIDX));
		SmartDashboard.putNumber("Drive Right Master Velocity", rightMaster.getSelectedSensorVelocity(SLOTIDX));
		SmartDashboard.putNumber("Drive Right Master Error", rightMaster.getClosedLoopError(SLOTIDX));
		SmartDashboard.putNumber("Drive Right Master Current", rightMaster.getOutputCurrent());
		SmartDashboard.putNumber("Drive Right Master Voltage", rightMaster.getMotorOutputVoltage());

		for (int i = 0; i < RobotMap.driveLeftFollowers.length; i++) {
			SmartDashboard.putNumber("Drive Left Follower"+i+" Current", leftFollower[i].getOutputCurrent());
			SmartDashboard.putNumber("Drive Left Follower"+i+" Voltage", leftFollower[i].getMotorOutputVoltage());
		}
		for (int i = 0; i < RobotMap.driveRightFollowers.length; i++) {
			SmartDashboard.putNumber("Drive Right Follower"+i+" Current", rightFollower[i].getOutputCurrent());
			SmartDashboard.putNumber("Drive Right Follower"+i+" Voltage", rightFollower[i].getMotorOutputVoltage());
		}
	}

	@Override
	public void pidWrite(double output) {
		if (output > ROTATE_MAX) {
			output = ROTATE_MAX;
		} else if (output > ROTATE_MIN) {
			// no change
		} else if (output > 0) {
			output = ROTATE_MIN;
		} else if (output == 0) {
			output = 0;
		} else if (output > (0 - ROTATE_MIN)) {
			output = 0 - ROTATE_MIN;
		} else if (output < (0 - ROTATE_MAX)) {
			output = 0 - ROTATE_MAX;
		}

		wheelSpeed(output, -output);
	}

	public void initDefaultCommand() {
		if (SPLIT_ARCADE) {
			setDefaultCommand(new DriveSplitArcade());
		} else {
			setDefaultCommand(new DriveTank());
		}
	}
}
