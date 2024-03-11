// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
  XboxController controller = new XboxController(0);
  Spark pickupWheels = new Spark(0);
  CANSparkMax r_FrontShoot = new CANSparkMax(8, MotorType.kBrushless);//Shooter motor
  CANSparkMax r_BackShoot = new CANSparkMax(7, MotorType.kBrushless);//Shooter motor
  CANSparkMax l_FrontShoot = new CANSparkMax(10, MotorType.kBrushless);//Shooter motor
  CANSparkMax l_BackShoot = new CANSparkMax(9, MotorType.kBrushless);//Shooter motor
  CANSparkMax lFrontDrive = new CANSparkMax(4, MotorType.kBrushless);
  CANSparkMax lBackDrive = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax rFrontDrive = new CANSparkMax(2,MotorType.kBrushless);
  CANSparkMax rBackDrive = new CANSparkMax(3, MotorType.kBrushless);
  
 // DifferentialDrive tempShooter;
  DifferentialDrive differentialDrive;

  SlewRateLimiter driveLimit = new SlewRateLimiter(0.5);
//Compressor compressor = new Compressor(1, PneumaticsModuleType.CTREPCM);
  DoubleSolenoid ShootBars;
  DoubleSolenoid Head;
  DoubleSolenoid Pickup;

  public DriveTrain() {
  //  compressor.enableDigital();
    differentialDrive = new DifferentialDrive(lFrontDrive, rFrontDrive);
  //  tempShooter = new DifferentialDrive(l_FrontShoot, r_FrontShoot);
    l_BackShoot.follow(l_FrontShoot);
    r_BackShoot.follow(r_FrontShoot);
    lBackDrive.follow(lFrontDrive);
    rBackDrive.follow(rFrontDrive);
    lFrontDrive.setInverted(true);
    lBackDrive.setInverted(true);
    rFrontDrive.setInverted(false); // Not explicitly needed, used as more of a safety
    l_FrontShoot.setInverted(false);// Not explicitly needed, used as more of a safety
   r_FrontShoot.setInverted(false);
   

  }
  boolean xboxXbutton = controller.getRawButton(3);
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run  
if (xboxXbutton==true){
  l_FrontShoot.set(-.35);
  r_FrontShoot.set(-.35);
}
  else {
    l_FrontShoot.stopMotor();
    r_FrontShoot.stopMotor();
  }
}
 
  public void driveWithJoysticks(XboxController controller, double DRIVETRAINSPEED){
  
    differentialDrive.arcadeDrive(controller.getLeftY()*DRIVETRAINSPEED, controller.getLeftX()*DRIVETRAINSPEED); 
    
    }
  
  


  public void driveForward(double AUTONOMOUS_SPEED){
    differentialDrive.tankDrive(-AUTONOMOUS_SPEED, -AUTONOMOUS_SPEED);
  }
  
  public void driveBackward(double AUTONOMOUS_SPEED){
      differentialDrive.tankDrive(AUTONOMOUS_SPEED, AUTONOMOUS_SPEED);
  }
  
  public void stop(){
    differentialDrive.stopMotor();
  }
}
