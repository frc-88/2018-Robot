package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.RobotMap;
import org.usfirst.frc.team88.robot.commands.DingleballIn;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sam R.
 * 
 */
public class Dingleball extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Servo leftDingle;
	private Servo rightDingle;
	
	public Dingleball(){
		leftDingle = new Servo(RobotMap.leftDingle);
		rightDingle = new Servo(RobotMap.rightDingle);
	}
	
	public void set(double value){
		leftDingle.set(value);
	}
	public void ballsFoward(){
		leftDingle.set(1);
		rightDingle.set(0);
	}
	
	public void ballsBackward(){
		leftDingle.set(0);
		rightDingle.set(1);
	}
	
	public void ballsDown(){
		leftDingle.set(0.5);
		rightDingle.set(0.5);
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber("Dingle Position", leftDingle.get());
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DingleballIn());
    }
}

