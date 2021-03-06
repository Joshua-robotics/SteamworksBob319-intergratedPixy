// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc319.SteamworksBob319;

import edu.wpi.cscore.VideoSource;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc319.SteamworksBob319.commands.*;
import org.usfirst.frc319.SteamworksBob319.commands.AutoModes.BlueGearThenHopperAuto;
import org.usfirst.frc319.SteamworksBob319.commands.AutoModes.BlueHopperAuto;
import org.usfirst.frc319.SteamworksBob319.commands.AutoModes.GearOnlyAuto;
import org.usfirst.frc319.SteamworksBob319.commands.AutoModes.RedGearThenHopperAuto;
import org.usfirst.frc319.SteamworksBob319.commands.AutoModes.RedHopperAuto;
import org.usfirst.frc319.SteamworksBob319.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;
	SendableChooser autoChooser;

	public static OI oi;

	public static driveTrain driveTrain;
	public static shooter shooter;
	public static fuelCollector fuelCollector;
	public static rollervator rollervator;
	public static gearCollector gearCollector;
	public static brakePad brakePad;
	// public static activeFloor activeFloor; // should be deleted (TG 2/15/17)
	public static compressor compressor;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();

		driveTrain = new driveTrain();
		shooter = new shooter();
		fuelCollector = new fuelCollector();
		rollervator = new rollervator();
		gearCollector = new gearCollector();
		brakePad = new brakePad();
		// activeFloor = new activeFloor(); // should be deleted (TG 2/15/17)
		compressor = new compressor();
		CameraServer.getInstance().startAutomaticCapture();

		autoChooser = new SendableChooser();
		autoChooser.addDefault("Default", new AutonomousCommand());
		autoChooser.addObject("BluehopperAuto", new BlueHopperAuto());
		autoChooser.addObject("BlueGearThenHopperAuto", new BlueGearThenHopperAuto());
		autoChooser.addObject("GearOnlyAuto", new GearOnlyAuto());
		autoChooser.addObject("RedHopperAuto", new RedHopperAuto());
		autoChooser.addObject("RedGearThenHopperAuto", new RedGearThenHopperAuto());

		SmartDashboard.putData("Autoonomus Command chooser", autoChooser);
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		// instantiate the command used for the autonomous period

		// autonomousCommand = new BlueHopperAuto();

	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {

		Robot.driveTrain.shiftUp();
		Robot.driveTrain.setDrivetrainProfileHighGear();
		autonomousCommand = (Command) autoChooser.getSelected();
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();

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
		Robot.driveTrain.shiftUp();
		Robot.driveTrain.setDrivetrainProfileHighGear();

		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("shooter speed", Robot.shooter.getShooterSpeed());
		SmartDashboard.putNumber("rollervator speed", Robot.rollervator.getRollervatorSpeed());
		SmartDashboard.putNumber("left drivetrain velocity", Robot.driveTrain.getLeftDriveVelocity());
		SmartDashboard.putNumber("right drivetrain 'velocity", Robot.driveTrain.getRightDriveVelocity());
		SmartDashboard.putNumber("Gear Arm position", Robot.gearCollector.gearArmAngle());
		SmartDashboard.putNumber("RollerVator Test Mode motorspeed", Robot.rollervator.rollervatorLead.getSpeed());
		SmartDashboard.putNumber("motorspeed", Robot.shooter.shooterLead.getSpeed());
		SmartDashboard.putNumber("Collector Current", Robot.gearCollector.getGearCollectorCurrent());
		SmartDashboard.putNumber("Rollervator Current", Robot.rollervator.getRollervatorCurrent());
		SmartDashboard.putNumber("Shooter Error", Robot.shooter.getShooterError());
		SmartDashboard.putBoolean("IsHighGear", Robot.driveTrain.isHighGear);
		SmartDashboard.putNumber("DT P value high gear = .3  low gear = 1", Robot.driveTrain.leftDriveLead.getP());
		SmartDashboard.putNumber("left Motor Setpoint = ", Robot.driveTrain.leftDriveLead.getSetpoint());
		SmartDashboard.putNumber("left drive train velocity error", Robot.driveTrain.leftdriveTrainError());
		SmartDashboard.putNumber("rollervator lead output voltage", Robot.rollervator.rollervatorLead.getOutputVoltage());
		SmartDashboard.putNumber("rollervator follow output voltage", Robot.rollervator.rollervatorFollow.getOutputVoltage());
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
