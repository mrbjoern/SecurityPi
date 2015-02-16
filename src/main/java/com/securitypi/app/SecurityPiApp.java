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

        // TODO: Start module controller and set up sensors.

        // TODO: Implement logging from sensors.

        // TODO: Implement interface for interaction with server.

        new SecurityPiApp().initialize();
    }

    private void initialize() {
        LogModule lm = new LogModule();
        lm.addSystemEventToLog("System started.");

        ModuleController mc = new ModuleController(lm);
    }
}
