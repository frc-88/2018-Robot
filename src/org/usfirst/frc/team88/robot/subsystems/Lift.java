package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {
private static final int SLOTIDX = 0;
private static final int TIMEOUTMS = 0;
private static final int FORWARDLIMIT = 0;
private static final int REVERSELIMIT = 0;
private TalonSRX liftTalon;
private VictorSPX rightwing;
   public Lift(){
	   liftTalon = new TalonSRX(RobotMap.liftTalon);
	   rightwing = new VictorSPX (RobotMap.rightwing);
	   
	   liftTalon.configSelectedFeedbackSensor(FeedbackDevice.Analog, SLOTIDX, TIMEOUTMS);
	   liftTalon.configForwardSoftLimitThreshold(FORWARDLIMIT, TIMEOUTMS);
	   liftTalon.configForwardSoftLimitEnable(true, TIMEOUTMS);
	   
	   liftTalon.configReverseSoftLimitThreshold(REVERSELIMIT, TIMEOUTMS);
	   liftTalon.configReverseSoftLimitEnable(true, TIMEOUTMS);
	  
		rightwing.set(ControlMode.Follower, RobotMap.liftTalon);
		
   }

   public void levitate(double velocity) {
	   liftTalon.set(ControlMode.PercentOutput, velocity);
   }
   
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

