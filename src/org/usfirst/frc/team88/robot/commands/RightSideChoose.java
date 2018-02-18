package org.usfirst.frc.team88.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class RightSideChoose extends ConditionalCommand {

    public RightSideChoose() {
        super(new RightSideSwitch(), new RightSideChooseScale());
    }

    
    protected boolean condition() {
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(0) == 'R'){
    		return true;
    	}
    	
    	return false;
    }

}
