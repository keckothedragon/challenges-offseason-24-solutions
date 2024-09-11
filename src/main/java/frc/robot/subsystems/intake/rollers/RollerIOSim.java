package frc.robot.subsystems.intake.rollers;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.Constants.IntakeConstants;

public class RollerIOSim implements RollerIO {
  private DCMotorSim m_sim;

  // store voltage as variable since sim doesn't have method to get voltage
  private double m_voltage;

  public RollerIOSim() {
    DCMotor gearbox = DCMotor.getKrakenX60(1);
    double gearing = IntakeConstants.kRollerGearing;
    double jKgMetersSquared = IntakeConstants.kRollerJKgMetersSquared;

    m_sim = new DCMotorSim(gearbox, gearing, jKgMetersSquared);
  }

  @Override
  public void updateInputs(RollerInputs inputs) {
    m_sim.update(0.02);

    inputs.voltage = getVoltage();
    inputs.velocityRadPerSec = getVelocityRadPerSec();
  }

  @Override
  public void setVoltage(double voltage) {
    m_sim.setInputVoltage(voltage);
    m_voltage = voltage;
  }

  @Override
  public double getVoltage() {
    return m_voltage;
  }

  @Override
  public double getVelocityRadPerSec() {
    return m_sim.getAngularVelocityRadPerSec();
  }
}
