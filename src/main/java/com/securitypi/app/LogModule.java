package com.securitypi.app;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Handles logging of events to logfile.
 */
public class LogModule {

    private String pathToLogile;
    private String logfileName;

    /**
     * Default constructor. Use predefined path and filename.
     */
    public LogModule() {
        setDefaultLocation();
        logfileName = "log.txt";
    }

    /**
     * Constructor for initializing log module and set up writing
     * to logfile. If no logfile exists, a new file will be created.
     * Either parameter may be null. If so, default values will be used.
     * @param path Full path
     * @param name Name of logfile
     */
    public LogModule(String path, String name) {

        if(path == null) {
            setDefaultLocation();
        }
        else {
            pathToLogile = path;
        }

        if(name == null) {
            logfileName = "log.txt";
        }
        else {
            logfileName = name;
        }

    }

    /**
     * Adds a line with sensor readings to the logfile. Including
     * current temperature and if motion is detected or not.
     * @param temperature Temperature in Celsius.
     * @param motionDetected True if motion is detected.
     * @return True if line was added successfully.
     */
    public boolean addSensorReadingToLog(double temperature, boolean motionDetected) {
        if(writeToLog("Temp: " + temperature + "c, Motion detected: " + motionDetected)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Adds a line with messages about system events to the logfile.
     * This may include when the system is turned on or off, if a
     * sensor is malfunctioning etc.
     * @param systemMessage System message to be written.
     * @return True if line was added successfully.
     */
    public boolean addSystemEventToLog(String systemMessage) {
        if(writeToLog(systemMessage)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Get the current system time and date to be written in logfile.
     * @return Date and time on format yyyy-MM-dd HH:mm:ss
     */
    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date);
    }

    /**
     * Append message to logfile if it exists, or else create a new file.
     * @param message Message to be written.
     * @return True if write is successful.
     */
    private boolean writeToLog(String message) {

        if(!checkDirectory()) {
            return false;
        }

        String filename = pathToLogile + logfileName;
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

    private boolean checkDirectory() {
        File logPath = new File(pathToLogile);

        if(!logPath.exists()) {
            // Directory does not exist, and we must create it.
            try {
                logPath.mkdirs();
                System.out.println("New path for logfiles created at " + pathToLogile);
                return true;
            }
            catch (SecurityException e) {
                System.err.println("Directory could not be created. Check permissions.");
                return false;
            }
        }
        else {
            return true;
        }
    }

    private boolean setDefaultLocation() {
        String username = System.getProperty("user.name");

        pathToLogile = "/home/" + username + "/securitypi/log/";
        return true;
    }

}
