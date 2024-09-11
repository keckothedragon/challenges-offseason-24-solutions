package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private IntakeIO m_io;
  public final IntakeInputsAutoLogged m_inputs;

  public Intake(IntakeIO io) {
    m_io = io;
    m_inputs = new IntakeInputsAutoLogged();
  }

  @Override
  public void periodic() {
    m_io.updateInputs(m_inputs);
  }

  public void setRollerVoltage(double voltage) {
    m_io.setRollerVoltage(voltage);
  }

  public Command setVoltageCommand(double voltage) {
    return Commands.runOnce(() -> m_io.setRollerVoltage(voltage));
  }

  public IntakeInputsAutoLogged getInputs() {
    return m_inputs;
  }
}
