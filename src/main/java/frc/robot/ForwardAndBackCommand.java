// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ForwardAndBackCommand extends SequentialCommandGroup {
  /** Creates a new ForwardAndBackCommand. */
  public ForwardAndBackCommand() {
    // addCommands(new FooCommand(), new BarCommand());
    addCommands (new RunPropellorCommand (.05), new RunPropellorCommand(-0.5));
    // Use addRequirements() here to declare subsystem dependencies.
  }
}
