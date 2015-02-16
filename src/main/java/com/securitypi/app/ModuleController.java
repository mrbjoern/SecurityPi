package com.securitypi.app;

/**
 * Module for controlling interfaces and other modules. The
 * controller is responsible for starting different components
 * and modules, logging events to logfile and handling input
 * from sensor components.
 */
public class ModuleController {
    private LogModule lm;

    private boolean state;
    private SensorHandler sensorHandler;

    /**
     * When initialized, the module is inactive and sensors
     * is turned OFF. Some sensors require some time to start
     * and the module controller should not start reading
     * until all sensors are ready. Waiting on sensors should
     * be handled by the sensor handler. The Module Controller
     * should block until sensors are ready.
     */
    public ModuleController(LogModule lm) {
        this.lm = lm;
        state = false;

        sensorHandler = new SensorHandler();

        if(sensorHandler.getState()) {
            lm.addSystemEventToLog("Sensor components started successfully.");
            state = true;
        }
        else {
            lm.addSystemEventToLog("Initialisation of sensors failed. System will not report activity on sensors.");
        }

        System.out.println(sensorHandler.getTemperature());
    }

    /**
     * Checks the state of sensors and modules.
     * @return True if on, false if not.
     */
    public boolean getState() {
        return state;
    }
}
