// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Chassis;

public class RobotMoveAndStayStraight extends CommandBase {
  PigeonIMU gyro = new PigeonIMU(2);
  double originalAngle = gyro.getFusedHeading();
  Chassis chassis;
  
  /** Creates a new robotMoveAndStayStraight. */
  public RobotMoveAndStayStraight(Chassis chassis) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(chassis);
    this.chassis = chassis;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() { chassis.setPower(0.5, 0.5);}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (originalAngle != gyro.getFusedHeading()){
      chassis.setPower(-0.1, 0.1);
    } else{
      chassis.setPower(0.5, 0.5);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    while(chassis.motorLB.getSelectedSensorPosition() != Constants.countsToMeter*2){}
    return true;
  }
}
