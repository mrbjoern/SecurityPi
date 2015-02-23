package com.securitypi.app;

import com.pi4j.io.gpio.*;

/**
 * Generic class for sensors.
 */
public class Sensor {
    private String id;
    private Pin pinId;

    private GpioPinDigitalInput sensor;

    /**
     * Initialize the sensor and set up for listening/reading/writing
     * @param sensorId Name of the sensor
     * @param pin Pin used
     * @param gpio Gpio controller
     */
    public Sensor(String sensorId,  Pin pin, GpioController gpio) {
        id = sensorId;
        pinId = pin;

        sensor = gpio.provisionDigitalInputPin(pinId, id, PinPullResistance.PULL_DOWN);
    }

    public String getId() {
        return id;
    }

    public Pin getPin() {
        return pinId;
    }

    /**
     * Shut down the sensor and release pin(s).
     * @return True if shut down, false if not.
     */
    public boolean shutDownSensor() {
        sensor.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        return true;
    }
}
