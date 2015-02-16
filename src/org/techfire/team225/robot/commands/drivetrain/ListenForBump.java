package org.techfire.team225.robot.commands.drivetrain;

import org.techfire.team225.robot.CommandBase;
import org.techfire.team225.robot.commands.autonomous.ListenForBumpAndSetArm;

public class ListenForBump extends CommandBase {
	
	boolean wasBumped;
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if (mecanumDrivetrain.getAccelerometerY() > 0.5) {
			wasBumped = true;
		}
	}

	@Override
	protected boolean isFinished() {
		return wasBumped;
	}

	@Override
	protected void end() {		
	}
}