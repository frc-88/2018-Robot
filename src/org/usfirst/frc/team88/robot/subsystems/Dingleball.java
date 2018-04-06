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
 *
 *
 * Instead of a haiku, I offer a song.
 *
 * Dingleballs
 *    by Mr. Nick
 *
 * Dashing across the field
 * With a power cube yellow
 * To the switch we speed,
 * Yeah, we're not going slow!
 * Motors start to spin
 * Making the lift rise
 * What fun it is to watch our bot
 * And sing this next reprise!
 * 
 * Oh, dingleballs, dingleballs
 * Floopy dingleballs
 * Oh, what fun it is to score
 * From over a foot away
 * Dingleballs, dingleballs
 * Floopy dingleballs
 * Oh, what fun it is to score
 * From over a foot away
 * 
 * A match or two ago,
 * We thought we'd take the scale,
 * And soon it tilted left
 * The wind was in our sail!
 * The other switch was bare
 * Misfortune for that lot
 * We got a cube from the portal,
 * And then their score was naught!
 * 
 * Oh, dingleballs, dingleballs
 * Floopy dingleballs
 * Oh, what fun it is to score
 * From over a foot away
 * Dingleballs, dingleballs
 * Floopy dingleballs
 * Oh, what fun it is to score
 * From over a foot away
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

