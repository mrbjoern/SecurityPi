package com.securitypi.app;

import java.text.DecimalFormat;

/**
 * Class for handling sensors on the pi.
 *
 * The SecurityPi project is currently designed to work with a small
 * set of components. At the time of writing, only a motion sensor and
 * temperature sensor. The sensor handler should not be handling reading
 * and writing to components like LEDs or buttons.
 */
public class ComponentHandler {
    // --- Temperature sensor ---
    // This is the sensor range with +/- 0.5 degrees accuracy. Measuring
    // temperatures outside of this range should not be common and should
    // be treated as a malfunction or misreading.
    private final double MIN_TEMP = -10.0;
    private final double MAX_TEMP = 85.0;

    private boolean state;

    // TODO: Implement sensor handler. RaspberryPi and sensors are needed for this
    // We probably need different classes for the sensors, with their
    // own event handlers here.

    /**
     * Set up sensors. Run sanity checks.
     */
    public ComponentHandler() {
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

        // TODO: Implement proper temperature reading

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
    public boolean startHandler() {
        // TODO: Implement sensors before implementing this function.

        state = true;
        return true;
    }

    /**
     * Stop the handler, stop listening and writing to components.
     * @return True if everything is OK.
     */
    public boolean stopHandler() {
        // TODO: Implement sensors before implementing this function.

        state = false;
        return true;
    }


}
