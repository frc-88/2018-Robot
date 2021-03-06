package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.Robot;
import org.usfirst.frc.team88.robot.RobotMap;
import org.usfirst.frc.team88.robot.commands.lift.LiftBasicControl;
import org.usfirst.frc.team88.robot.commands.lift.LiftMove;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * <pre>
 * high up in the air
 * placing cubes like we don't care
 * scale tips scoring points
 *  *
 * </pre>
 */
public class Lift extends Subsystem {
	private static final boolean BASIC_CONTROL = false;

	public static final int POS_BOTTOM = 2000;
	public static final int POS_ALMOST_BOTTOM = 2010;
	public static final int POS_SWITCH = 2020;
	public static final int POS_LOW_SCALE = 2030;
	public static final int POS_MID_SCALE = 2040;
	public static final int POS_HI_SCALE = 2050;
	public static final int POS_SAFE = 2060;
	public static final int POS_SAFE_BOTTOM = 2070;

	private static final int SLOTIDX = 0;
	private static final int TIMEOUTMS = 0;
	private static final double RAMPRATE = .30;
	private static final double MAX_SPEED = 50;
	private static final int CRUISE_VELOCITY = 50;
	private static final int ACCELERATION = 500;
	private static final double P = 4.0;
	private static final double I = 0.0; // Make sure reverse limit is accurate
											// before using i!!!
	private static final double D = 150.0;
	private static final double F = (1023 / MAX_SPEED) * 0.9;

	private static final int FORWARD_LIMIT_BASE = 730;
	private static final int POS_BOTTOM_BASE = 2;
	private static final int POS_SWITCH_BASE = 250;
	private static final int POS_LOW_SCALE_BASE = 470;
	private static final int POS_MID_SCALE_BASE = 570;
	private static final int POS_HI_SCALE_BASE = 670;
	private static final int POS_SAFE_BASE = 210;
	private static final int POS_SAFE_BOTTOM_BASE = 180;
	private static final int UP_OFFSET = 220; // TODO

	private static final int DISTANCE_THRESHOLD = 100;

	private int posReverseLimit;
	private int posForwardLimit;
	private int posBottom;
	private int posSwitch;
	private int posLowScale;
	private int posMidScale;
	private int posHighScale;
	private int posSafe;
	private int posSafeBottom;

	private TalonSRX master;
	private TalonSRX follower;
	private TalonSRX follower2;

	private int position;

	public Lift() {
		master = new TalonSRX(RobotMap.liftMaster);
		follower = new TalonSRX(RobotMap.liftFollower);
		follower2 = new TalonSRX(RobotMap.liftFollower2);

		/* analog signal with no wrap-around (0-3.3V) */
		master.configSelectedFeedbackSensor(FeedbackDevice.Analog, SLOTIDX, TIMEOUTMS);

		/* eFeedbackNotContinuous = 1, subValue/ordinal/timeoutMs = 0 */
		master.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, TIMEOUTMS);

		master.config_kP(SLOTIDX, P, TIMEOUTMS);
		master.config_kI(SLOTIDX, I, TIMEOUTMS);
		master.config_kD(SLOTIDX, D, TIMEOUTMS);
		master.config_kF(SLOTIDX, F, TIMEOUTMS);
		master.configNominalOutputForward(0.0, TIMEOUTMS);
		master.configNominalOutputReverse(0.0, TIMEOUTMS);
		master.configPeakOutputForward(+1.0, TIMEOUTMS);
		master.configClosedloopRamp(RAMPRATE, TIMEOUTMS);
		master.configPeakOutputReverse(-1.0, TIMEOUTMS);
		master.configNeutralDeadband(0.04, TIMEOUTMS);
		master.configPeakCurrentLimit(25, TIMEOUTMS);
		master.configPeakCurrentDuration(0, TIMEOUTMS);
		master.configContinuousCurrentLimit(20, TIMEOUTMS);
		master.enableCurrentLimit(true);
		master.setSensorPhase(false);
		master.setNeutralMode(NeutralMode.Brake);

		master.configMotionCruiseVelocity(CRUISE_VELOCITY, TIMEOUTMS);
		master.configMotionAcceleration(ACCELERATION, TIMEOUTMS);

		zeroOnPref();
		
		follower.setInverted(true);
		follower.setNeutralMode(NeutralMode.Brake);
		follower.follow(master);

		follower2.setInverted(true);
		follower2.setNeutralMode(NeutralMode.Brake);
		follower2.follow(master);
		
		position = getPosition();
	}

	public void basicMotion(double input) {
		Preferences prefs = Preferences.getInstance();

		input += prefs.getDouble("LiftGravityOffset", 0.0);

		master.set(ControlMode.PercentOutput, input);
	}

	public void gotoPosition() {
		master.set(ControlMode.MotionMagic, position, DemandType.ArbitraryFeedForward, 0.07);
	}
	
	public void setPosition(int target){
		setPosition(target, false);
	}
	public void setPosition(int target, boolean dangermode) {
		target = positionMap(target, dangermode);

		if (target < posReverseLimit) {
			target = posReverseLimit;
		} else if (target > posForwardLimit) {
			target = posForwardLimit;
		}

		if (target < posSafe && !Robot.arm.isDown() && !dangermode) {
			target = posSafe;
		}
		
		position = target;
	}

	public int getPosition() {
		return master.getSelectedSensorPosition(SLOTIDX);
	}

	public double getPercentHeight() {
		return (double) (getPosition() - posReverseLimit) / (double) FORWARD_LIMIT_BASE;
	}

	public boolean onTarget(int target){
			return onTarget(target, false);
	}
	public boolean onTarget(int target, boolean dangermode) {
		target = positionMap(target, dangermode);

		return Math.abs(master.getSelectedSensorPosition(SLOTIDX) - target) < DISTANCE_THRESHOLD;
	}
	
	public boolean belowTarget(int target){
		return belowTarget(target, false);
	}
	public boolean belowTarget(int target, boolean dangermode) {
		target = positionMap(target, dangermode);

		return master.getSelectedSensorPosition(SLOTIDX) < target;
	}

	public double getMasterCurrent() {
		return master.getOutputCurrent();
	}

	public void setReverseLimit() {
		master.configForwardSoftLimitThreshold(posForwardLimit, TIMEOUTMS);
		master.configForwardSoftLimitEnable(true, TIMEOUTMS);
		master.configReverseSoftLimitThreshold(posReverseLimit, TIMEOUTMS);
		master.configReverseSoftLimitEnable(true, TIMEOUTMS);
		master.overrideLimitSwitchesEnable(true);

		follower.configForwardSoftLimitEnable(false, TIMEOUTMS);
		follower.configReverseSoftLimitEnable(false, TIMEOUTMS);
		follower.overrideLimitSwitchesEnable(false);

		follower2.configForwardSoftLimitEnable(false, TIMEOUTMS);
		follower2.configReverseSoftLimitEnable(false, TIMEOUTMS);
		follower2.overrideLimitSwitchesEnable(false);
	}

	public void disableSoftLimits() {
		master.configForwardSoftLimitEnable(false, TIMEOUTMS);
		master.configReverseSoftLimitEnable(false, TIMEOUTMS);
		master.overrideLimitSwitchesEnable(false);
	}

	private void zeroOnPref() {
		Preferences prefs = Preferences.getInstance();
		
		setGlobalPositionValues(prefs.getInt("LiftBottom", 289));
		setReverseLimit();
	}

	public void zeroOnCurrentPosition() {
		Preferences prefs = Preferences.getInstance();
		int position = getPosition();
		
		prefs.putInt("LiftBottom", position);
		setGlobalPositionValues(position);
		setReverseLimit();
	}
	
	private void setGlobalPositionValues(int reverseLimit) {
		posReverseLimit = reverseLimit;
		posForwardLimit = posReverseLimit + FORWARD_LIMIT_BASE;
		posSafeBottom = posReverseLimit + POS_SAFE_BOTTOM_BASE;
		posSafe = posReverseLimit + POS_SAFE_BASE;
		posBottom = posReverseLimit + POS_BOTTOM_BASE;
		posSwitch = posReverseLimit + POS_SWITCH_BASE;
		posLowScale = posReverseLimit + POS_LOW_SCALE_BASE;
		posMidScale = posReverseLimit + POS_MID_SCALE_BASE;
		posHighScale = posReverseLimit + POS_HI_SCALE_BASE;
	}	

	private int positionMap(int position) {
		return positionMap(position, false);
	}
	
	@SuppressWarnings("unused")
	private int positionMap(int position, boolean dangermode) {
		if (dangermode || Robot.arm.isDown()) {
			switch (position) {
			case POS_BOTTOM:
				return posBottom;
			case POS_ALMOST_BOTTOM:
				return posBottom + 90;
			case POS_SWITCH:
				return posSwitch;
			case POS_LOW_SCALE:
				return posLowScale;
			case POS_MID_SCALE:
				return posMidScale;
			case POS_HI_SCALE:
				return posHighScale;
			case POS_SAFE:
				return posSafe;
			case POS_SAFE_BOTTOM:
				return posSafeBottom;
			default:
				return position;
			}
		} else {
			switch (position) {
			case POS_BOTTOM:
				return posSafe;
			case POS_ALMOST_BOTTOM:
				return posSafe + 40;
			case POS_SWITCH:
				return posSafe;
			case POS_LOW_SCALE:
				return posLowScale - UP_OFFSET;
			case POS_MID_SCALE:
				return posMidScale - UP_OFFSET;
			case POS_HI_SCALE:
				return posHighScale - UP_OFFSET;
			case POS_SAFE:
				return posSafe;
			case POS_SAFE_BOTTOM:
				return posSafeBottom;
			default:
				return position;
			}
		}
	}

	public void updateDashboard() {
		SmartDashboard.putNumber("Lift Forward Limit", posForwardLimit);
		SmartDashboard.putNumber("Lift High Scale", positionMap(POS_HI_SCALE));
		SmartDashboard.putNumber("Lift Mid Scale", positionMap(POS_MID_SCALE));
		SmartDashboard.putNumber("Lift Low Scale", positionMap(POS_LOW_SCALE));
		SmartDashboard.putNumber("Lift Switch", positionMap(POS_SWITCH));
		SmartDashboard.putNumber("Lift Safe ", positionMap(POS_SAFE));
		SmartDashboard.putNumber("Lift Safe Bottom", positionMap(POS_SAFE_BOTTOM));
		SmartDashboard.putNumber("Lift Almost Bottom", positionMap(POS_ALMOST_BOTTOM));
		SmartDashboard.putNumber("Lift Bottom", positionMap(POS_BOTTOM));
		SmartDashboard.putNumber("Lift Reverse Limit", posReverseLimit);

		SmartDashboard.putNumber("Lift Target Position", position);
		SmartDashboard.putNumber("Lift Master Position", getPosition());

		SmartDashboard.putNumber("Lift Master Velocity", master.getSelectedSensorVelocity(SLOTIDX));
		SmartDashboard.putNumber("Lift Master Error", master.getClosedLoopError(SLOTIDX));
		SmartDashboard.putNumber("Lift Master Current", master.getOutputCurrent());
		SmartDashboard.putNumber("Lift Master Voltage", master.getMotorOutputVoltage());
		SmartDashboard.putNumber("Lift Follower Current", follower.getOutputCurrent());
		SmartDashboard.putNumber("Lift Follower Voltage", follower.getMotorOutputVoltage());

		SmartDashboard.putNumber("Lift Percent Height", getPercentHeight());
		SmartDashboard.putBoolean("Lift Position High Scale?", onTarget(positionMap(POS_HI_SCALE)));
		SmartDashboard.putBoolean("Lift Position Mid Scale?", onTarget(positionMap(POS_MID_SCALE)));
		SmartDashboard.putBoolean("Lift Position Low Scale?", onTarget(positionMap(POS_LOW_SCALE)));
		SmartDashboard.putBoolean("Lift Position Switch?", onTarget(positionMap(POS_SWITCH)));
		SmartDashboard.putBoolean("Lift Position Bottom?", onTarget(positionMap(POS_BOTTOM)));
		
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		if (BASIC_CONTROL) {
			setDefaultCommand(new LiftBasicControl());
		} else {
			setDefaultCommand(new LiftMove());
		}
	}
}
