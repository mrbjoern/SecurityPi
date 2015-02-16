package com.securitypi.app;

/**
 * Module for controlling interfaces and other modules.
 */
public class ModuleController {

    private boolean state;
    private ComponentHandler componenthandler;

    /**
     * When initialized, the module is inactive and sensors
     * is turned OFF. Some sensors require some time to start
     * and the module controller should not start reading
     * until all sensors are ready.
     */
    public ModuleController() {
        state = false;

        componenthandler = new ComponentHandler();
    }

    /**
     * Checks the state of sensors and modules.
     * @return True if on, false if not.
     */
    public boolean getState() {
        return state;
    }
}
