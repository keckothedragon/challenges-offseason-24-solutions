package frc.robot.subsystems.intake.rollers;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.Constants.IntakeConstants;

public class RollerIOSim implements RollerIO {
  // use DCMotorSim as detailed in README
  private DCMotorSim m_sim;

  // store voltage as variable since sim doesn't have method to get voltage
  private double m_voltage;

  public RollerIOSim() {
    // use physical constants from IntakeConstants and kraken x60 as detailed in README
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
    // set input voltage and store it for later
    m_sim.setInputVoltage(voltage);
    m_voltage = voltage;
  }

  @Override
  public double getVoltage() {
    // self-explanatory
    return m_voltage;
  }

  @Override
  public double getVelocityRadPerSec() {
    // also self-explanatory
    return m_sim.getAngularVelocityRadPerSec();
  }
}
