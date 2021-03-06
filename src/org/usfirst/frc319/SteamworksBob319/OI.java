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

import org.usfirst.frc319.SteamworksBob319.CommandGroups.FuelCollectorAndHopperFlapRetract;
import org.usfirst.frc319.SteamworksBob319.CommandGroups.SmartShoot;
import org.usfirst.frc319.SteamworksBob319.commands.*;
import org.usfirst.frc319.SteamworksBob319.commands.DriveTrain.FollowTrajectory;
//import org.usfirst.frc319.SteamworksBob319.commands.DriveTrain.FollowBothMotionProfiles;
import org.usfirst.frc319.SteamworksBob319.commands.DriveTrain.JoystickDrive;
import org.usfirst.frc319.SteamworksBob319.commands.DriveTrain.ShiftToggle;
import org.usfirst.frc319.SteamworksBob319.commands.FuelCollector.FuelCollectorDeploy;
import org.usfirst.frc319.SteamworksBob319.commands.FuelCollector.FuelCollectorDeployWaitThenHopperFlap;
import org.usfirst.frc319.SteamworksBob319.commands.FuelCollector.FuelCollectorIn;
import org.usfirst.frc319.SteamworksBob319.commands.FuelCollector.FuelCollectorRetract;
import org.usfirst.frc319.SteamworksBob319.commands.FuelCollector.FuelCollectorStop;
import org.usfirst.frc319.SteamworksBob319.commands.FuelCollector.FuelCollectorToggle;
import org.usfirst.frc319.SteamworksBob319.commands.FuelCollector.HopperFlapDeploy;
import org.usfirst.frc319.SteamworksBob319.commands.FuelCollector.HopperFlapRetract;
import org.usfirst.frc319.SteamworksBob319.commands.GearCollector.AutomatedCollectGearAndLift;
import org.usfirst.frc319.SteamworksBob319.commands.GearCollector.DepositGear;
import org.usfirst.frc319.SteamworksBob319.commands.GearCollector.GearCollectorArmDeploy;
import org.usfirst.frc319.SteamworksBob319.commands.GearCollector.GearCollectorArmGoToDepositGear;
import org.usfirst.frc319.SteamworksBob319.commands.GearCollector.GearCollectorIn;
import org.usfirst.frc319.SteamworksBob319.commands.GearCollector.GearCollectorOut;
import org.usfirst.frc319.SteamworksBob319.commands.GearCollector.GearCollectorArmRetract;
import org.usfirst.frc319.SteamworksBob319.commands.GearCollector.GearCollectorStop;
import org.usfirst.frc319.SteamworksBob319.commands.GearCollector.LightsOff;
import org.usfirst.frc319.SteamworksBob319.commands.GearCollector.LightsOn;
import org.usfirst.frc319.SteamworksBob319.commands.GearCollector.RetractCollectorThenStopCollect;
import org.usfirst.frc319.SteamworksBob319.commands.Rollervator.AutomatedRollervatorClimb;
import org.usfirst.frc319.SteamworksBob319.commands.Rollervator.RollervatorClimb;
import org.usfirst.frc319.SteamworksBob319.commands.Rollervator.RollervatorGo;
import org.usfirst.frc319.SteamworksBob319.commands.Rollervator.RollervatorStop;
import org.usfirst.frc319.SteamworksBob319.commands.Shooter.ShooterGoToSpeed;
import org.usfirst.frc319.SteamworksBob319.commands.Shooter.ShooterStop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc319.SteamworksBob319.subsystems.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public BobController driverController;
	public BobController operatorController;
	public XboxController testController;

	public OI() {

		driverController = new BobController(0);
		operatorController = new BobController(1);
		
		

		// --------OPERATOR---------------//

		operatorController.aButton.whenPressed(new AutomatedCollectGearAndLift());
		operatorController.bButton.whenPressed(new DepositGear());
		operatorController.yButton.whenPressed(new RetractCollectorThenStopCollect());
		
		operatorController.rightTriggerButton.whenPressed(new SmartShoot());
		operatorController.rightBumper.whenPressed(new ShooterStop());
		
		operatorController.leftTriggerButton.whenPressed(new AutomatedRollervatorClimb());
		operatorController.leftBumper.whenPressed(new RollervatorStop());
		
		operatorController.selectButton.whenPressed(new FuelCollectorAndHopperFlapRetract());
		operatorController.startButton.whenPressed(new FuelCollectorDeployWaitThenHopperFlap());
		
		// -----------DRIVER-----------//

		driverController.leftBumper.whenPressed(new FuelCollectorToggle(1));
		driverController.rightBumper.whenPressed(new ShiftToggle());		
		driverController.bButton.whenPressed(new ShooterRollervatorStop());

	}

	public Joystick getdriverController() {
		return driverController;
	}

	public Joystick getoperatorController() {
		return operatorController;
	}

}
