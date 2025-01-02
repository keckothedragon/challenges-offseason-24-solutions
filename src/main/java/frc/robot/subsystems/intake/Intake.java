package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  // create members as described in the README
  private IntakeIO m_io;
  public final IntakeInputsAutoLogged m_inputs;

  public Intake(IntakeIO io) {
    // instantiate members as described in the README
    m_io = io;
    m_inputs = new IntakeInputsAutoLogged();
  }

  @Override
  public void periodic() {
    // update the inputs each loop
    m_io.updateInputs(m_inputs);
  }

  public void setRollerVoltage(double voltage) {
    // very simple
    m_io.setRollerVoltage(voltage);
  }

  public Command setVoltageCommand(double voltage) {
    // use Commands.runOnce to create a command that sets the roller voltage when scheduled
    return Commands.runOnce(() -> m_io.setRollerVoltage(voltage));
  }

  public IntakeInputsAutoLogged getInputs() {
    // also simple
    return m_inputs;
  }
}
