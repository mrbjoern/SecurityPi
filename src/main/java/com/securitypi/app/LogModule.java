package com.securitypi.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Handles logging of events to logfile. Path to logfile should
 * be defined in the config.txt file.
 *
 * Log files are for logging purpose only. To avoid enormous file
 * sizes a max file size should be set in config.txt. The system
 * will also hold a backup of up to one max size log file.
 */
public final class LogModule {

    /**
     * This is a final class with only static methods. No constructor
     * is needed.
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
        String message = "Temp: " + temperature + "C\tMotion detected: " + motionDetected;

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
        String filename = ConfigHandler.getLogFile();

        // Check if the size of the logfile is not to big.
        // If the file is to big, it will be taken a temporary
        // backup before the logfile is eventually deleted.
        long sizeOfLogFile = getLogFileSize(filename);
        long maxSize = ConfigHandler.getMaxsize() * 1000;

        if(sizeOfLogFile >= maxSize) {
            backupLogFile(filename);
        }

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

    /**
     * Renames current logfile to FILE.bak, if this file already
     * exists, it will be deleted.
     * @param path Path to logfile
     */
    private static void backupLogFile(String path) {
        File logfile = new File(path);
        File backupFile = new File(path + ".bak");

        if(backupFile.exists()) {
            if(!backupFile.delete()) {
                System.err.println("Error deleting old backup file. Please check permissions.");
            }
        }

        if(!logfile.renameTo(new File(path + ".bak"))) {
            System.err.println("Error in taking backup of logfile. Another process might use the logfile.");
        }
    }

    private static long getLogFileSize(String path) {
        File logfile = new File(path);

        return logfile.length();
    }
}
