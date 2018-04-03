package org.usfirst.frc.team88.robot.subsystems;

import org.usfirst.frc.team88.robot.RobotMap;
import org.usfirst.frc.team88.robot.commands.DingleballIn;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sam R.
 * watch them soar outward
 * flooping to and fro, watch as
 * our dingle balls fly
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
		rightDingle.set(1 - value);
	}
	
	public void ballsFoward(){
		set(1);
	}
	
	public void ballsBackward(){
		set(0);
	}
	
	public void ballsDown(){
		set(0.5);
	}
	
	public void updateDashboard() {
		SmartDashboard.putNumber("Dingle Left Position", leftDingle.get());
		SmartDashboard.putNumber("Dingle Left Position", rightDingle.get());
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(null);
    }
}

