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

    /**
     * SecurityPi supports a few configuration parameters.
     *
     * -h (--help) to print help menu
     * -v (--version) to print version number
     *
     * @param args configuration parameters
     */
    public static void main(String[] args) {

        if(args.length < 0) {
            if(args[0].compareTo("-v") == 0 || args[0].compareTo("--version") == 0) {
                System.out.println(getVersion());
                System.exit(0);
            }
            else if(args[0].compareTo("-h") == 0 || args[0].compareTo("--help") == 0) {
                printHelp();
                System.exit(0);
            }
            else {
                System.out.println("Not a valid parameter. Try -h for help.");
                System.exit(0);
            }
        }
        else {
            initialize();
        }

    }

    private static void initialize() {
        new ModuleController();
    }

    private static void printHelp() {

    }

    private static String getVersion() {
        return "Version 0.1";
    }
}
