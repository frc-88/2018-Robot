package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.RobotMap;
import org.usfirst.frc.team88.robot.commands.LiftBasicControl;
import org.usfirst.frc.team88.robot.commands.LiftMove;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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
	private static final boolean BASIC_CONTROL = true;

	public static final int POS_BOTTOM = 2000;
	public static final int POS_ALMOST_BOTTOM = 2010;
	public static final int POS_SWITCH = 2020;
	public static final int POS_LOW_SCALE = 2030;
	public static final int POS_MID_SCALE = 2040;
	public static final int POS_HI_SCALE = 2050;

	private static final int SLOTIDX = 0;
	private static final int TIMEOUTMS = 0;
	private static final double RAMPRATE = .30;
	private static final double MAX_SPEED = 32;
	private static final int CRUISE_VELOCITY = 30;
	private static final int ACCELERATION = 180;
	private static final double P = 20.0;
	private static final double I = 0.0; // Make sure reverse limit is accurate before using i!!!
	private static final double D = 80.0;
	private static final double F = 1023 / MAX_SPEED;

	private static final int FORWARD_LIMIT_BASE = 700;
	private static final int POS_BOTTOM_BASE = 2;
	private static final int POS_SWITCH_BASE = 250;
	private static final int POS_LOW_SCALE_BASE = 470;
	private static final int POS_MID_SCALE_BASE = 570;
	private static final int POS_HI_SCALE_BASE = 670;

	private static final int DISTANCE_THRESHOLD = 20;

	private int posReverseLimit;
	private int posForwardLimit;
	private int posBottom;
	private int posSwitch;
	private int posLowScale;
	private int posMidScale;
	private int posHighScale;

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
		master.configPeakOutputReverse(-1.0, TIMEOUTMS);
		master.configNeutralDeadband(0.04, TIMEOUTMS);
		master.configClosedloopRamp(RAMPRATE, TIMEOUTMS);
		master.setSensorPhase(false);
		master.setNeutralMode(NeutralMode.Brake);

		master.configMotionCruiseVelocity(CRUISE_VELOCITY, TIMEOUTMS);
		master.configMotionAcceleration(ACCELERATION, TIMEOUTMS);

		// no clue if this is correct....the zero, in particular, is a mystery
		setGlobalPositionValues((int) master.configGetParameter(ParamEnum.eReverseSoftLimitThreshold, 0, TIMEOUTMS));

		follower.follow(master);
		follower.setInverted(true);
		
		follower2.follow(master);
		follower2.setInverted(true);
		
		position = getPosition();
	}

	public void basicMotion(double input) {
		master.set(ControlMode.PercentOutput, input);
	}

	public void gotoPosition() {
		master.set(ControlMode.MotionMagic, position);
	}

	public void setPosition(int target) {
		target = positionMap(target);
		
		if (target < posReverseLimit) {
			target = posReverseLimit;
		} else if (target > posForwardLimit) {
			target = posForwardLimit;
		}

		position = target;
	}

	public int getPosition() {
		return master.getSelectedSensorPosition(SLOTIDX);
	}

	public double getPercentHeight() {
		return (double) (getPosition() - posReverseLimit) / (double) FORWARD_LIMIT_BASE;
	}

	public boolean onTarget(int target) {
		target = positionMap(target);
		
		return Math.abs(master.getSelectedSensorPosition(SLOTIDX) - target) < DISTANCE_THRESHOLD;
	}

	public boolean belowTarget(int target) {
		target = positionMap(target);
		
		return master.getSelectedSensorPosition(SLOTIDX) < target;
	}

	public double getMasterCurrent() {
		return master.getOutputCurrent();
	}

	// This should only be called when at the reverse limit, used only be calibration routine
	public void setReverseLimit() {
		setGlobalPositionValues(getPosition());

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
	
	private void setGlobalPositionValues(int reverseLimit) {
		posReverseLimit = reverseLimit;
		posForwardLimit = posReverseLimit + FORWARD_LIMIT_BASE;
		posBottom = posReverseLimit + POS_BOTTOM_BASE;
		posSwitch = posReverseLimit + POS_SWITCH_BASE;
		posLowScale = posReverseLimit + POS_LOW_SCALE_BASE;
		posMidScale = posReverseLimit + POS_MID_SCALE_BASE;
		posHighScale = posReverseLimit + POS_HI_SCALE_BASE;
	}

	private int positionMap(int position) {
		switch (position) {
		case POS_BOTTOM:
			return posBottom;
		case POS_ALMOST_BOTTOM:
			return posBottom + 70;
		case POS_SWITCH:
			return posSwitch;
		case POS_LOW_SCALE:
			return posLowScale;
		case POS_MID_SCALE:
			return posMidScale;
		case POS_HI_SCALE:
			return posHighScale;
		default:
			return position;
		}
	}

	public void updateDashboard() {
		SmartDashboard.putNumber("Lift Forward Limit", posForwardLimit);
		SmartDashboard.putNumber("Lift High Scale", posHighScale);
		SmartDashboard.putNumber("Lift Mid Scale", posMidScale);
		SmartDashboard.putNumber("Lift Low Scale", posLowScale);
		SmartDashboard.putNumber("Lift Switch", posSwitch);
		SmartDashboard.putNumber("Lift Bottom", posBottom);
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
		SmartDashboard.putBoolean("Lift Position High Scale?", onTarget(posHighScale));
		SmartDashboard.putBoolean("Lift Position Mid Scale?", onTarget(posMidScale));
		SmartDashboard.putBoolean("Lift Position Low Scale?", onTarget(posLowScale));
		SmartDashboard.putBoolean("Lift Position Switch?", onTarget(posSwitch));
		SmartDashboard.putBoolean("Lift Position Bottom?", onTarget(posBottom));
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
