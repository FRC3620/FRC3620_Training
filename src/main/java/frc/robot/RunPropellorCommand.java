// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunPropellorCommand extends CommandBase {
  double savedPower;
  Timer timer;

  /** Creates a new RunPropellorCommand. */
  public RunPropellorCommand(double power) {
    savedPower = power;
    timer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.propellorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double power = savedPower;
    if (timer.hasElapsed(2.0)) {//check for 2s
    power = savedPower/3; //one third power
  } else if (timer.hasElapsed(1.0)) {// check for 1s
  power = savedPower/2; //half power
  } else {
  power = savedPower; // full power
}
    RobotContainer.propellorSubsystem.spinPropellor(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    RobotContainer.propellorSubsystem.spinPropellor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(5.0);
  }
}
