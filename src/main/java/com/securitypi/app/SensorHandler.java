package com.securitypi.app;

import java.text.DecimalFormat;

/**
 * Class for handling sensors on the pi.
 *
 * The SecurityPi project is currently designed to work with a small
 * set of components. At the time of writing, only a motion sensor and
 * temperature sensor.
 */
public class SensorHandler {
    private final double MIN_TEMP = -30.0;
    private final double MAX_TEMP = 100.0;

    private boolean state;

    // TODO: Implement sensor handler. RaspberryPi and sensors are needed for this
    // We probably need different classes for the sensors, with their
    // own event handlers here.

    /**
     * Set up sensors. Run sanity checks.
     */
    public SensorHandler() {
        state = false;

        // Start the component handler.
        startHandler();
    }

    /**
     * Get the state of the component handler.
     * @return state
     */
    public boolean getState() {
        return state;
    }

    /**
     * Read the temperature from sensor.
     * @return Temperature in Celsius.
     */
    public double getTemperature() {
        double temperature = Math.random() * (40.0 - (-20.0) + (-20.0));

        if(temperature > MAX_TEMP || temperature < MIN_TEMP) {
            // Sensor values are out of range. Probably a malfunction.
            return -100.0;
        }

        return Math.round(temperature*10)/10.0d;
    }

    /**
     * Check if motion is detected.
     * @return True if motion, false if not.
     */
    public boolean getMotion() {
        return false;
    }

    /**
     * Start the handler, listening and writing to components.
     * @return True if everything went OK.
     */
    private boolean startHandler() {
        // TODO: Implement sensors before implementing this function.

        state = true;
        return true;
    }

    /**
     * Stop the handler, stop listening and writing to components.
     * @return True if everything is OK.
     */
    private boolean stopHandler() {
        // TODO: Implement sensors before implementing this function.

        state = false;
        return true;
    }


}
