package frc.robot.subsystems.wrist;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wrist extends SubsystemBase {
  private WristIO m_io;
  private PIDController m_controller;
  public final WristInputsAutoLogged m_inputs;

  final double TOLERANCE_DEG = 3;

  public Wrist(WristIO io, PIDController controller) {
    m_io = io;
    m_controller = controller;
    m_inputs = new WristInputsAutoLogged();

    m_controller.setTolerance(TOLERANCE_DEG);
  }

  @Override
  public void periodic() {
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
    return Commands.runOnce(() -> setDesiredAngle(angle));
  }

  public boolean withinTolerance() {
    // since the controller knows our current position, setpoint, and tolerance, it knows if we're
    // within tolerance
    return m_controller.atSetpoint();
  }

  public WristInputsAutoLogged getInputs() {
    return m_inputs;
  }
}
