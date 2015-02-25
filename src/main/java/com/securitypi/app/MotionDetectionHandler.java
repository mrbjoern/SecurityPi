package com.securitypi.app;

/**
 * The motion detection handler. Listens on motion sensor
 * when activated. Also listens on button presses and input
 * from external interfaces like the SecurityPi Server so
 * the motion detection can be turned on or off.
 */
public class MotionDetectionHandler implements Runnable {

    private volatile boolean active;

    private SensorHandler sensorHandler;

    MotionDetectionHandler(SensorHandler sh) {
        active = false;
        sensorHandler = sh;
    }

    public void terminate() {
        active = false;
    }

    @Override
    public void run() {
        while(active) {
            // TODO: Implement listening on button changes
        }
    }
}
