// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
  
public class Chassis extends SubsystemBase {
  /** Creates a new Chassic. */
    public TalonFX motorLB;
    public TalonFX motorLF;
    public TalonFX motorRF;
    public TalonFX motorRB;
    
    public Chassis() {
      motorLB = new TalonFX(Constants.leftBackMotorID);
      motorLF = new TalonFX(Constants.leftFrontMotorID);
      motorRF = new TalonFX(Constants.rightFrontMotorID);
      motorRB = new TalonFX(Constants.rightBackMotorID);
    }

    public void setPower(double leftMotrs, double rightMotors) {
      motorLB.set(ControlMode.PercentOutput, leftMotrs);
      motorLF.follow(motorLB);
      motorRB.set(ControlMode.PercentOutput, rightMotors);
      motorRF.follow(motorRB);
      motorLF.setInverted(true);
      motorLB.setInverted(true);
    }

    
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
