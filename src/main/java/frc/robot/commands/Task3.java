// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class Task3 extends CommandBase {
  PigeonIMU gyro = new PigeonIMU(2);
  double originalAngle = gyro.getFusedHeading();
  Chassis chassis;
  double angle;

  /** Creates a new TurnRobot. */
  public Task3(Chassis chassis, double angle) {
    addRequirements(chassis);
    this.angle = angle;
    this.chassis = chassis;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putData(this);
    chassis.setPower(-0.1, 0.1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {chassis.setPower(0, 0);}
  
  public double getAngle() {return gyro.getFusedHeading(); }
  public double getOriginalAngle() {return originalAngle; }
  
  @Override
  public void initSendable(SendableBuilder builder) {
      // TODO Auto-generated method stub
      super.initSendable(builder);
      builder.addDoubleProperty("angle", this::getAngle, null);
      builder.addDoubleProperty("original angle", this::getOriginalAngle, null);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(gyro.getFusedHeading() < originalAngle+angle){return true;}
    return false;  
  }
}
