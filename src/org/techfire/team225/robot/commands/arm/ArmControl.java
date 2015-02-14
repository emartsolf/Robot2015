package org.techfire.team225.robot.commands.arm;

import org.techfire.team225.robot.CommandBase;
import org.techfire.team225.robot.OI;

public class ArmControl extends CommandBase {
	
	public ArmControl() {
		requires(arm);
	}
	
	@Override
	protected void initialize() {
		arm.setTarget(arm.getPosition());
	}

	@Override
	protected void execute() {
		double throttle = OI.driver.getRawAxis(2)- OI.driver.getRawAxis(3);
		if (throttle != 0) {
			arm.setTarget(arm.getPosition());
			arm.setMotorSpeed(throttle);
		} else {
			arm.updatePID();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

}
