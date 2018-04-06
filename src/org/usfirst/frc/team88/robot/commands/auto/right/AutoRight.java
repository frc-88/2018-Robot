package org.usfirst.frc.team88.robot.commands.auto.right;

import org.usfirst.frc.team88.robot.commands.PowerUpConditionalCommand;
import org.usfirst.frc.team88.robot.commands.auto.AutoCrossTheLine;

public class AutoRight extends PowerUpConditionalCommand {

	public AutoRight() {
//		super(new AutoRightSideFarScale(), // LL
//				new AutoRightSideScale(), // LR
//				new AutoRightSideFarScale(), // RL
//				new AutoRightSideScale()); // RR
		super(new AutoCrossTheLine(),
				new AutoCrossTheLine(),
				new AutoCrossTheLine(),
				new AutoCrossTheLine());
	}

}
