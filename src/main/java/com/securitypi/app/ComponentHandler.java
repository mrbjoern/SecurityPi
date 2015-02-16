package com.securitypi.app;

/**
 * Class for handling sensors on the pi.
 *
 * The SecurityPi project is currently designed to work with a small
 * set of components. At the time of writing, only a motion sensor and
 * temperature sensor.
 */
public class ComponentHandler {

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
