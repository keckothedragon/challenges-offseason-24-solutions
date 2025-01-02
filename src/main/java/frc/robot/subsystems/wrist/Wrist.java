package frc.robot.subsystems.wrist;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wrist extends SubsystemBase {
  // create members as defined in the README
  private WristIO m_io;
  private PIDController m_controller;
  public final WristInputsAutoLogged m_inputs;

  public Wrist(WristIO io, PIDController controller) {
    // instantiate members
    m_io = io;
    m_controller = controller;
    m_inputs = new WristInputsAutoLogged();
  }

  @Override
  public void periodic() {
    // update inputs
    m_io.updateInputs(m_inputs);

    // since we store setpoint in the controller we only need to pass one value to calculate()
    // we use degrees as detailed in the README
    // (the PID values are tuned for degrees so radians dont work)
    double pidVoltage = m_controller.calculate(Units.radiansToDegrees(m_inputs.angleRad));
    m_io.setVoltage(pidVoltage);
  }

  public void setDesiredAngle(Rotation2d angle) {
    // you can store the desired angle in the pid controller or as its own variable
    m_controller.setSetpoint(angle.getDegrees());
  }

  public Command setDesiredAngleCommand(Rotation2d angle) {
    // same as last challenge we use Commands.runOnce to create a command to set the desired angle
    // when scheduled
    return Commands.runOnce(() -> setDesiredAngle(angle));
  }

  public boolean withinTolerance() {
    // since the controller knows our current position, setpoint, and tolerance, it knows if we're
    // within tolerance
    return m_controller.atSetpoint();
  }

  public WristInputsAutoLogged getInputs() {
    // self-explanatory
    return m_inputs;
  }
}
