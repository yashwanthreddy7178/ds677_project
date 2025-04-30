package robo.model.event.sensor.weather;

import robo.model.MeasurementUnit;
import robo.model.event.sensor.SensorEvent;
import robo.model.sensor.weather.TemperatureSensor;

/**
 * Event used to be created by temperature sensors.
 * 
 * @author Mircea Diaconescu
 * @date May 15, 2014, 11:06:57 PM
 * 
 */
public class TemperatureSensorEvent extends SensorEvent {
  // temperature = -275 (below zero absolute) => no real value was read
  // (uninitialized value) from the sensor
  private double temperature = -275;
  protected MeasurementUnit.Thermic.Temperature unit = MeasurementUnit.Thermic.Temperature.Celsius;

  public double getTemperature() {
    return temperature;
  }

  public MeasurementUnit.Thermic.Temperature getUnit() {
    return unit;
  }

  public TemperatureSensorEvent(TemperatureSensor source, double temperature,
      MeasurementUnit.Thermic.Temperature unit) {
    super(source);
    this.temperature = temperature;
    this.unit = unit;
  }
}
