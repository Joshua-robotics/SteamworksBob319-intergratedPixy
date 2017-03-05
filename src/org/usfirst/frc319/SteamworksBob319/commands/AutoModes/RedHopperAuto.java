// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc319.SteamworksBob319.commands.AutoModes;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc319.SteamworksBob319.CommandGroups.FuelCollectorDeployThenCollect;
import org.usfirst.frc319.SteamworksBob319.CommandGroups.SmartShoot;
import org.usfirst.frc319.SteamworksBob319.commands.DoNothing;
import org.usfirst.frc319.SteamworksBob319.commands.DriveTrain.FollowTrajectory;
import org.usfirst.frc319.SteamworksBob319.commands.FuelCollector.FuelCollectorDeploy;
import org.usfirst.frc319.SteamworksBob319.commands.FuelCollector.FuelCollectorStop;
import org.usfirst.frc319.SteamworksBob319.commands.Shooter.ShooterGoToSpeed;
import org.usfirst.frc319.SteamworksBob319.subsystems.*;

/**
 *
 */
public class RedHopperAuto extends CommandGroup {


  
    public RedHopperAuto() {

    	addParallel(new FuelCollectorDeploy());
    	addSequential(new FollowTrajectory("RedHopperAutoPt1"));//stage 1
    	addSequential(new DoNothing(),1.5);// do nothing 3 seconds
    	addSequential(new FollowTrajectory("RedHopperAutoPt2"));//stage 2
    	addSequential(new FollowTrajectory("RedHopperAutoPt3"));//stage 3
    	//addParallel(new FuelCollectorStop());
    	addSequential(new SmartShoot());
    	
 
    } 
}
