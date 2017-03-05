// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc319.SteamworksBob319.subsystems;

import org.usfirst.frc319.SteamworksBob319.Robot;
import org.usfirst.frc319.SteamworksBob319.RobotMap;
import org.usfirst.frc319.SteamworksBob319.commands.*;
import org.usfirst.frc319.SteamworksBob319.commands.DriveTrain.JoystickDrive;
import org.usfirst.frc319.SteamworksBob319.commands.DriveTrain.VelocityDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class driveTrain extends Subsystem {

    
    public final CANTalon leftDriveLead = RobotMap.driveTrainLeftDriveLead;
    public final CANTalon rightDriveLead = RobotMap.driveTrainRightDriveLead;
    private final DoubleSolenoid shifter = RobotMap.driveTrainShifter;
    private final CANTalon leftDriveFollow = RobotMap.driveTrainLeftDriveFollow;
    private final CANTalon rightDriveFollow = RobotMap.driveTrainRightDriveFollow;
    private final RobotDrive joystickDrive = RobotMap.driveTrainJoystickDrive;
    
    public boolean isHighGear;
    public final int LOW_GEAR_PROFILE = 1;
    public final int HIGH_GEAR_PROFILE = 0;
    
    double pVelocityDrive = .4;
	double fVelocityDrive = .147;
	double dVelocityDrive = 0.0;
	double cLRRVelocity = 0.0;
    
    StringBuilder _sb = new StringBuilder();
    int _loops = 0;
    
   public driveTrain (){
	   
	   rightDriveLead.changeControlMode(TalonControlMode.PercentVbus);
	   rightDriveFollow.changeControlMode(TalonControlMode.Follower);
	   rightDriveFollow.set(rightDriveLead.getDeviceID());
	   rightDriveLead.reverseOutput(false);
	   rightDriveLead.reverseSensor(false);
	   rightDriveLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
	   rightDriveLead.configEncoderCodesPerRev(1024);
	   
	   leftDriveLead.changeControlMode(TalonControlMode.PercentVbus);
	   leftDriveFollow.changeControlMode(TalonControlMode.Follower);
	   leftDriveFollow.set(leftDriveLead.getDeviceID());
	   leftDriveLead.reverseOutput(true);
	   leftDriveLead.reverseSensor(true);
	   leftDriveLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
	   leftDriveLead.configEncoderCodesPerRev(1024);
	   
   		rightDriveLead.configNominalOutputVoltage(+0.0f, -0.0f);
   		rightDriveLead.configPeakOutputVoltage(+12.0f, -12.0f);
   		
   		
   		leftDriveLead.configNominalOutputVoltage(+0.0f, -0.0f);
   		leftDriveLead.configPeakOutputVoltage(+12.0f, -12.0f);
   		
   		double pHighGear = .3;//.5
   		double pLowGear = 1.0;//.3
   		double fGainHighGear = .147;
   		double fGainLowGear = 0.320; //.320
   		double dLowGear = 100.0;//75 //start at plowgear
   		double dHighGear = 75.0;//0
   		double closedLoopRampRate = 72.0;
   		
   		
   		
   		
   		rightDriveLead.setPID(pHighGear, 0, dHighGear, fGainHighGear, 0, closedLoopRampRate, HIGH_GEAR_PROFILE); // high gear
   		rightDriveLead.setPID(pLowGear, 0, dLowGear, fGainLowGear, 0, closedLoopRampRate, LOW_GEAR_PROFILE); // low gear
   		leftDriveLead.setPID(pHighGear, 0, dHighGear, fGainHighGear, 0, closedLoopRampRate, HIGH_GEAR_PROFILE); // high gear
   		leftDriveLead.setPID(pLowGear, 0, dLowGear, fGainLowGear, 0, closedLoopRampRate, LOW_GEAR_PROFILE); // low gear
   
   		
   }
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
      

        //setDefaultCommand(new JoystickDrive());
    	
    	  setDefaultCommand(new VelocityDrive());
        //setDefaultCommand(new RightDrivetrainPIDTest());
        //setDefaultCommand(new LeftDrivetrainPIDTest());
                                   //change to left or rightdriveTrainPIDTestMode
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    
    }
    
    public int getLeftDrivetrainEncoder(){
    	return -leftDriveLead.getEncPosition();
    }
    public int getRightDrivetrainEncoder(){
    	return rightDriveLead.getEncPosition();
    }
    	
    public void joystickDrive(double moveValue, double rotateValue){
    	joystickDrive.arcadeDrive(moveValue, rotateValue, true);
    }
    
    public void shiftUp(){
    	shifter.set(DoubleSolenoid.Value.kForward);
    	
    	isHighGear = true;
    }
    
    public void shiftDown(){
    	shifter.set(DoubleSolenoid.Value.kReverse);
    	
    	isHighGear = false;
    }
    
    public double leftdriveTrainError(){
    	return leftDriveLead.getSpeed()-leftDriveLead.getSetpoint();
    }
    
    public void setDrivetrainProfileLowGear(){
    	rightDriveLead.setProfile(LOW_GEAR_PROFILE);
    	leftDriveLead.setProfile(LOW_GEAR_PROFILE);
    }
    public void setDrivetrainProfileHighGear(){
    	rightDriveLead.setProfile(HIGH_GEAR_PROFILE);
    	leftDriveLead.setProfile(HIGH_GEAR_PROFILE);
    }
    
    public void setDrivetrainVelocityDrive(){
    	
    	
    	leftDriveLead.setP(pVelocityDrive);
    	leftDriveLead.setF(fVelocityDrive);
    	leftDriveLead.setD(dVelocityDrive);
    	leftDriveLead.setCloseLoopRampRate(cLRRVelocity);
    	
    	rightDriveLead.setP(pVelocityDrive);
    	rightDriveLead.setF(fVelocityDrive);
    	rightDriveLead.setD(dVelocityDrive);
    	rightDriveLead.setCloseLoopRampRate(cLRRVelocity);
    	
    	changeDriveTrainControlModeToSpeed();
    }
    
    
    public void changeDriveTrainControlModeToSpeed(){
    	leftDriveLead.changeControlMode(TalonControlMode.Speed);
    	rightDriveLead.changeControlMode(TalonControlMode.Speed);
    }
    
    public void setLeftRightMotors(double leftSpeed, double rightSpeed){
    	leftDriveLead.set(leftSpeed);
    	rightDriveLead.set(rightSpeed);
    }
    public double getRightDriveVelocity(){
    	return rightDriveLead.getSpeed();
    }
    public double getLeftDriveVelocity(){
    	return leftDriveLead.getSpeed();
    }
    
    public void rightDrivetrainPIDTestMode(){
  	  //SmartDashboard.putInt("motorspeed", rightDriveLead.getEncVelocity());
    	
    	/* get gamepad axis */
    	double leftYstick = Robot.oi.driverController.getRawAxis(1);
    	double motorOutput = rightDriveLead.getOutputVoltage() / rightDriveLead.getBusVoltage();
    	/* prepare line to print */
  		_sb.append("\tout:");
  		_sb.append(motorOutput);
        _sb.append("\tspd:");
        _sb.append(rightDriveLead.getSpeed() );
        
        if(Robot.oi.driverController.getRawButton(1)){
        	/* Speed mode */
        	double targetSpeed = 468;//Robot.oi.driverController.getRawAxis(1) * 1015; 
        	rightDriveLead.changeControlMode(TalonControlMode.Speed);
        	rightDriveLead.setProfile(1); // 0 = high gear, 1 = low gear
        	rightDriveLead.set(targetSpeed); 
        	//_sb.append(_talon.getControlMode() );
        	
        	//System.out.println(_talonFollower.getControlMode() );
        	/* append more signals to print when in speed mode. */
            _sb.append("\terr:");
            _sb.append(rightDriveLead.getClosedLoopError());
            _sb.append("\ttrg:");
            _sb.append(targetSpeed);
        } 
        else if (Robot.oi.driverController.getRawButton(2)){
        	rightDriveLead.set(-.6);
        	//System.out.println(_talon.getControlMode() );
        	//System.out.println(_talonFollower.getControlMode() );
        	//System.out.println("constant voltage mode");
        }
        
        else {
        	/* Percent voltage mode */
        	//System.out.println(_talon.getControlMode() );
        	//System.out.println(_talonFollower.getControlMode() );
        	rightDriveLead.changeControlMode(TalonControlMode.PercentVbus);
        	rightDriveLead.set(leftYstick);
        	//System.out.println("joystick vbus mode");
        }

        if(++_loops >= 10) {
        	_loops = 0;
        	System.out.println(_sb.toString());
        }
        _sb.setLength(0);
    }
    public void leftDrivetrainPIDTestMode(){
    	  //SmartDashboard.putInt("motorspeed", leftDriveLead.getEncVelocity());
      	
      	/* get gamepad axis */
      	double leftYstick = Robot.oi.driverController.getRawAxis(1);
      	double motorOutput = leftDriveLead.getOutputVoltage() / leftDriveLead.getBusVoltage();
      	/* prepare line to print */
    		_sb.append("\tout:");
    		_sb.append(motorOutput);
          _sb.append("\tspd:");
          _sb.append(leftDriveLead.getSpeed() );
          
          if(Robot.oi.driverController.getRawButton(1)){
          	/* Speed mode */
          	double targetSpeed = Robot.oi.driverController.getRawAxis(1) * 1015; /* ____ RPM in either direction */
          	leftDriveLead.changeControlMode(TalonControlMode.Speed);
          	leftDriveLead.setProfile(1); // 0 = high gear, 1 = low gear
          	leftDriveLead.set(targetSpeed); /* 1500 RPM in either direction */
          	//_sb.append(_talon.getControlMode() );
          	
          	//System.out.println(_talonFollower.getControlMode() );
          	/* append more signals to print when in speed mode. */
              _sb.append("\terr:");
              _sb.append(leftDriveLead.getClosedLoopError());
              _sb.append("\ttrg:");
              _sb.append(targetSpeed);
          } 
          /*else if (Robot.oi.driverController.getRawButton(2)){
          	leftDriveLead.set(-.6);
          	//System.out.println(_talon.getControlMode() );
          	//System.out.println(_talonFollower.getControlMode() );
          	//System.out.println("constant voltage mode");
          }*/
          
          else {
          	/* Percent voltage mode */
          	//System.out.println(_talon.getControlMode() );
          	//System.out.println(_talonFollower.getControlMode() );
          	leftDriveLead.changeControlMode(TalonControlMode.PercentVbus);
          	leftDriveLead.set(leftYstick);
          	//System.out.println("joystick vbus mode");
          }

          if(++_loops >= 10) {
          	_loops = 0;
          	System.out.println(_sb.toString());
          }
          _sb.setLength(0);
      }
}

