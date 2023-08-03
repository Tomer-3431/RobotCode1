// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Chassis;

public class Task2 extends CommandBase {
  double meter;
  Chassis chassis;
  /** Creates a new MoveNumOfMeters. */
  public Task2(Chassis chassis, double meter) {
    addRequirements(chassis);
    this.meter = meter;
    this.chassis = chassis;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putData(this);
    chassis.setPower(0.5,0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {chassis.setPower(0, 0);}

  public double getError() {return chassis.motorLB.getSelectedSensorPosition()-Constants.countsToMeter*meter; }
  
  @Override
  public void initSendable(SendableBuilder builder) {
      // TODO Auto-generated method stub
      builder.addDoubleProperty("Error", this::getError, null);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    while(chassis.motorLB.getSelectedSensorPosition() != Constants.countsToMeter*meter){}
    return true;
  }
}
