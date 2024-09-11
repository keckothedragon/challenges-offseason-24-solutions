package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class ArmIOKraken implements ArmIO {
  private TalonFX m_motor;

  public ArmIOKraken(int port) {
    m_motor = new TalonFX(port);
  }

  @Override
  public void setVoltage(double voltage) {
    m_motor.setControl(new VoltageOut(voltage));
  }

  @Override
  public double getVoltage() {
    return m_motor.getMotorVoltage().getValue();
  }

  @Override
  public double getVelocityRadiansPerSecond() {
    // convert rotations per sec to rad per sec
    return Units.rotationsToRadians(m_motor.getVelocity().getValue());
  }

  @Override
  public Rotation2d getPosition() {
    return Rotation2d.fromRotations(m_motor.getPosition().getValue());
  }

  @Override
  public Object getMotor() {
    // DO NOT MODIFY THIS METHOD
    return m_motor;
  }
}
