package org.techfire.team225.robot.commands.drivetrain;

import org.techfire.team225.robot.CommandBase;
import org.techfire.team225.robot.OI;

public class FireDrive extends CommandBase {

	double targetAngle = 0;

	public FireDrive() {
		requires(drivetrain);
	}
		
	@Override
	protected void initialize() {
		targetAngle = drivetrain.getGyro();
	}

	@Override
	protected void execute() {
		double yThrottle = OI.getDriverForwardThrottle(); // forward and backwards
		double xThrottle = OI.getDriverStrafeThrottle(); // side to side
		double rotationThrottle = OI.getDriverRotation();
		
		
		// joystick check
		if (Math.abs(yThrottle) < 0.1)
			yThrottle = 0;
		if (Math.abs(xThrottle) < 0.1)
			xThrottle = 0;
		if (Math.abs(rotationThrottle) < 0.1)
			rotationThrottle = 0;
		
		// omni-directional mecanum scaling
		double scale = 0.5 + (Math.abs(xThrottle) * 0.5);
		
		// gyro correction, holds the robot at the angle the driver wants it to be at
		if (Math.abs(rotationThrottle) > 0.1)
		{
			targetAngle = drivetrain.getGyro();
		}
		else
		{
			//rotationThrottle = (drivetrain.getGyro() - targetAngle) * -0.02;
		}
		drivetrain.setMotorSpeeds(-xThrottle, yThrottle, -rotationThrottle, scale, false);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drivetrain.setMotorSpeeds(0, 0, 0, 0, false);
	}
}
