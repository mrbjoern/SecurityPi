package com.securitypi.app;

/**
 * Module for controlling interfaces and other modules. The
 * controller is responsible for starting different components
 * and modules, logging events to logfile and handling input
 * from sensor components.
 */
public class ModuleController {
    private ConfigHandler ch;

    private boolean state;

    private SensorHandler sensorHandler;
    private EventLogger eventLogger;
    private Thread eventLoggerThread;

    // private final GpioController gpio = GpioFactory.getInstance();

    /**
     * When initialized, the module is inactive and sensors
     * is turned OFF. Some sensors require some time to start
     * and the module controller should not start reading
     * until all sensors are ready. Waiting on sensors should
     * be handled by the sensor handler. The Module Controller
     * should block until sensors are ready.
     */
    public ModuleController() {
        // Set up the config handler.
        ch = new ConfigHandler();

        LogModule.addSystemEventToLog("System started.");

        state = false;

        sensorHandler = new SensorHandler();

        if(sensorHandler.getState()) {
            LogModule.addSystemEventToLog("Sensor components started successfully.");
            state = true;

            // If the sensors are up and running, a listener should be set up
            // to check the activity on sensors on a specified time interval.
        }
        else {
            LogModule.addSystemEventToLog("Initialisation of sensors failed. System will not report activity on sensors.");
        }

        startListener();
    }

    /**
     * Checks the state of sensors and modules.
     * @return True if on, false if not.
     */
    public boolean getState() {
        return state;
    }

    private void startListener() {
        eventLogger = new EventLogger(sensorHandler);
        eventLoggerThread = new Thread(eventLogger);

        LogModule.addSystemEventToLog("Starting to log events with " + ConfigHandler.getTimeInterval() + " seconds interval.");

        eventLoggerThread.start();
    }

    private void stopListener() throws InterruptedException {
        LogModule.addSystemEventToLog("Stopping to log events.");
        if(eventLoggerThread != null) {
            eventLogger.terminate();
            eventLoggerThread.join();
        }
    }

    /**
     * Notify server on events.
     */
    private void notifyOnEvent(String event) {

    }
}
