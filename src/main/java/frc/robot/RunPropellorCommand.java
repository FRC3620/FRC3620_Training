// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.InstantCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RunPropellorCommand extends InstantCommand {

double savedPower;

  public RunPropellorCommand(double power) {
    savedPower = power; 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.propellorSubsystem);
  }

  public void execute(){
    RobotContainer.propellorSubsystem.spinPropellor(savedPower);
  }

  public void end(boolean interupted){
    RobotContainer.propellorSubsystem.spinPropellor(0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}
}
