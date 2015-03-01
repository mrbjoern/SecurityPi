package com.securitypi.app;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

/**
 * Module for controlling interfaces and other modules. The
 * controller is responsible for starting different components
 * and modules, logging events to logfile and handling input
 * from sensor components.
 *
 * The module controller should start handling components on
 * button press or with a command sent by server. Red and green
 * LED indicates if the module controller is active and listening
 * on sensors.
 */
public class ModuleController {
    private ConfigHandler ch;

    private boolean state;
    private boolean motionDetection;

    private ComponentHandler componentHandler;

    private EventLogger eventLogger;
    private Thread eventLoggerThread;

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
        motionDetection = false;

        componentHandler = new ComponentHandler();

        if(componentHandler.getState()) {
            LogModule.addSystemEventToLog("Sensor components started successfully.");
            state = true;

            // We can start the green LED now.
            startGreenLED();

            // If the sensors are up and running, a listener should be set up
            // to check the activity on sensors on a specified time interval.
            startEventLogger();
        }
        else {
            LogModule.addSystemEventToLog("Initialisation of sensors failed. System will not report activity on sensors.");
        }
    }

    /**
     * Checks the state of sensors and modules.
     * @return True if on, false if not.
     */
    public boolean getState() {
        return state;
    }

    /**
     * Start listening on regular events. This process should
     * run whether motion detection is active or not.
     */
    private void startEventLogger() {
        eventLogger = new EventLogger(componentHandler);
        eventLoggerThread = new Thread(eventLogger);

        LogModule.addSystemEventToLog("Starting to log events with " + ConfigHandler.getTimeInterval() + " seconds interval.");

        eventLoggerThread.start();
    }

    /**
     * Should only be triggered on system shutdown.
     * @throws InterruptedException
     */
    private void stopEventLogger() throws InterruptedException {
        LogModule.addSystemEventToLog("Stopping to log events.");
        if(eventLoggerThread != null) {
            eventLogger.terminate();
            eventLoggerThread.join();
        }
    }

    /**
     * Start the green LED
     * @return True if started
     */
    private boolean startGreenLED() {
        return true;
    }

    /**
     * Stop the green LED
     * @return True if stopped
     */
    private boolean stopGreenLED() {
        return true;
    }

    /**
     * Start the red LED
     * @return True if started
     */
    private boolean startRedLED() {
        return true;
    }

    /**
     * Stop the red LED
     * @return True if stopped
     */
    private boolean stopRedLED() {
        return true;
    }
}
