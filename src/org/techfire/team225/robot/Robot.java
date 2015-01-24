
package org.techfire.team225.robot;

import org.techfire.team225.robot.commands.autonomous.StrafeAndStackFlipped;
import org.techfire.team225.robot.commands.autonomous.StrafeAndStackNormal;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	Gyro gyro;
    Command autonomousCommand;
    
    CommandGroup[] autonomi;
    int selected = 0;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	CommandBase.init();
    	OI.init();
    	gyro = CommandBase.mecanumDrivetrain.gyro;
    	gyro.initGyro();
    	gyro.reset();
    	autonomi = new CommandGroup[] {
        		new StrafeAndStackNormal(),
        		new StrafeAndStackFlipped()
        };
    }
	
	public void disabledPeriodic() {
		selected += OI.getDriverDPadRightLeft();
		if (selected == 7) {
			selected = 0;
		} else if (selected == -1) {
			selected = 6;
		}
		SmartDashboard.putString("Selected Autonomous", autonomi[selected].toString());
		Timer.delay(1.0);
	}

    public void autonomousInit() {
    	autonomousCommand = autonomi[selected];
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        SmartDashboard.putNumber("Gyro", gyro.getAngle());
        SmartDashboard.putBoolean("Photosensor Left", 
        		!CommandBase.mecanumDrivetrain.photoLeft.get());
        SmartDashboard.putBoolean("Photosensor Right", 
        		!CommandBase.mecanumDrivetrain.photoRight.get());
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
