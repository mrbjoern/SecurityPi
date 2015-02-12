package com.securitypi.app;

/**
 * Module for controlling interfaces and other modules.
 */
public class ModuleController {

    private boolean state;
    private ComponentHandler componenthandler;

    /**
     * When initialized, the module is inactive and sensors
     * is turned OFF.
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

    /**
     * Turns the controller ON and handles sensors.
     * @return True if everything went OK.
     */
    public boolean setStateOn() {
        if(componenthandler.startHandler()) {
            state = true;
            return true;
        }
        else {
            state = false;
            return false;
        }
    }
}
