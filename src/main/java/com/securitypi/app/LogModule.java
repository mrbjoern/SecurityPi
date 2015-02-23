package com.securitypi.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Handles logging of events to logfile.
 */
public final class LogModule {

    // TODO: Implement static class for handling default config etc.

    private static String pathToLogfile;
    private static String logfileName;

    /**
     * Default constructor. Use predefined path and filename.
     */
    private LogModule() {
    }

    /**
     * Adds a line with sensor readings to the logfile. Including
     * current temperature and if motion is detected or not.
     * @param temperature Temperature in Celsius.
     * @param motionDetected True if motion is detected.
     * @return True if line was added successfully.
     */
    public static boolean addSensorReadingToLog(double temperature, boolean motionDetected) {
        String message = "Temp: " + temperature + "C, Motion detected: " + motionDetected;

        return writeToLog(message);
    }

    /**
     * Adds a line with messages about system events to the logfile.
     * This may include when the system is turned on or off, if a
     * sensor is malfunctioning etc.
     * @param systemMessage System message to be written.
     * @return True if line was added successfully.
     */
    public static boolean addSystemEventToLog(String systemMessage) {
        return writeToLog(systemMessage);
    }

    /**
     * Get the current system time and date to be written in logfile.
     * @return Date and time on format yyyy-MM-dd HH:mm:ss
     */
    private static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date);
    }

    /**
     * Append message to logfile if it exists, or else create a new file.
     * @param message Message to be written.
     * @return True if write is successful.
     */
    private static boolean writeToLog(String message) {

        // Get the logfile from config.
        String filename = ConfigHandler.getConfigFile();

        // Message to be written.
        String logMessage = getCurrentDate() + " - " + message;

        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(logMessage + "\n");
            fw.close();
        }
        catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            return false;
        }

        return true;
    }
}
