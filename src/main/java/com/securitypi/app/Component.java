package com.securitypi.app;


/**
 * Generic class for components.
 */
public class Component {
    private String id;
    private int pinId;

    /**
     * Initialize the component and set up for listening/reading/writing
     * @param componentId Name of the sensor
     * @param pin Pin used
     */
    public Component(String componentId, int pin) {
        id = componentId;
        pinId = pin;
    }

    public void setId(String componentId) {
        id = componentId;
    }

    public String getId() {
        if(id == null) {
            setId("Default");
        }
        return id;
    }

    public int getPin() {
        return pinId;
    }
}
