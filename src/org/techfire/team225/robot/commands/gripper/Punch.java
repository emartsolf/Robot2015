package org.techfire.team225.robot.commands.gripper;

import org.techfire.team225.robot.CommandBase;

public class Punch extends CommandBase {

	@Override
	protected void initialize() {
		gripper.togglePunch();
	}

	@Override
	protected void execute() {		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {		
	}

}