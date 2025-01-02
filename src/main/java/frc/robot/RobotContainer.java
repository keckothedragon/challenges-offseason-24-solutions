// Copyright 2021-2024 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.oi.DriverControls;
import frc.robot.oi.DriverControlsXbox;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.FlywheelIONeo;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
@SuppressWarnings("unused")
public class RobotContainer {
  // Subsystems
  private Flywheel m_flywheel;

  // Controller
  private DriverControls m_driverControls;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureSubsystems();
    configureControllers();
    configureButtonBindings();
  }

  private void configureSubsystems() {
    // instantiate Flywheel with FlywheelIONeo and PIDController
    m_flywheel =
        new Flywheel(
            new FlywheelIONeo(FlywheelConstants.kMotorPort),
            new PIDController(FlywheelConstants.kP, FlywheelConstants.kI, FlywheelConstants.kD));
  }

  private void configureControllers() {
    m_driverControls = new DriverControlsXbox(1);
  }

  private void configureButtonBindings() {
    // from README:
    // "Each of these commands should be run a single time when the button is pressed or released."
    // so use onTrue and onFalse
    // whileTrue and whileFalse are incorrect since they continuously run the command
    m_driverControls
        .runFlywheel()
        .onTrue(m_flywheel.setDesiredVelocityCommand(FlywheelConstants.kVelocitySetpoint))
        .onFalse(m_flywheel.setDesiredVelocityCommand(0));
  }
}
