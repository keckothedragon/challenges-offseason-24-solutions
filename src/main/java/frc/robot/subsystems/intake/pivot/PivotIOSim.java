package frc.robot.subsystems.intake.pivot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.Constants.IntakeConstants;

public class PivotIOSim implements PivotIO {
  // use SingleJointedArmSim as detailed in README
  private SingleJointedArmSim m_sim;

  // store voltage as variable since sim doesn't have method to get voltage
  private double m_voltage;

  public PivotIOSim() {
    // more physical constants from IntakeConstants and NEO as detailed in README
    DCMotor gearbox = DCMotor.getNEO(1);
    double gearing = IntakeConstants.kPivotGearing;
    double jKgMetersSquared = IntakeConstants.kPivotJKgMetersSquared;
    double armLength = IntakeConstants.kPivotLength;
    double minAngle = IntakeConstants.kPivotMinAngle; // radians
    double maxAngle = IntakeConstants.kPivotMaxAngle; // radians
    boolean simulateGravity = false; // detailed in README
    double startAngle = Units.degreesToRadians(120); // detailed in README

    m_sim =
        new SingleJointedArmSim(
            gearbox,
            gearing,
            jKgMetersSquared,
            armLength,
            minAngle,
            maxAngle,
            simulateGravity,
            startAngle);
  }

  @Override
  public void updateInputs(PivotInputs inputs) {
    m_sim.update(0.02);

    inputs.voltage = getVoltage();
    inputs.velocityRadPerSec = getVelocityRadPerSec();
    inputs.angleRad = getAngle().getRadians();
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
    return m_sim.getVelocityRadPerSec();
  }

  @Override
  public Rotation2d getAngle() {
    // pretty simple
    return Rotation2d.fromRadians(m_sim.getAngleRads());
  }
}
