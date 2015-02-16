package com.securitypi.app;

/**
 * The main class for the SecurityPi project. This starts and
 * configures required modules.
 *
 * The local interface on the SecurityPi is text based and should
 * be configured to only work with local shell. The interface may
 * handle logging of events and configuring the SecurityPi application.
 */
public class SecurityPiApp {

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        // TODO: Start module controller and set up sensors.

        // TODO: Implement logging from sensors.

        LogModule lm = new LogModule();
        lm.addSystemEventToLog("This is a test.");
        lm.addSensorReadingToLog(3.14, true);
        //System.out.println(lm.getCurrentDate());

        // TODO: Implement interface for interaction with server.

    }
}
