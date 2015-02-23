package com.securitypi.app;

import java.io.*;

/**
 * Handles config parameters from config file.
 */
public class ConfigHandler {

    private static File configFile;

    private static String logfilePath;
    private static String logfileName;
    private static int timeInterval;

    ConfigHandler() {
        // Config file should be in the root directory of SecurityPi.
        configFile = new File(System.getProperty("user.dir") + "/src/main/resources/config.txt");
        System.out.println(configFile.toString());

        // We must check if the config file exists. If not, an error
        // should be reported.

        if(configFile.exists() && !configFile.isDirectory()) {
            // Everything is good. Start reading configs.
            readConfig();
            checkConfig();
        }
        else {
            // A config file was not found. System can not start.
            System.out.println("Config file not found. Could not start.");
            System.exit(1);
        }
    }

    /**
     * Get the time interval used for sensor reporting. This is the
     * time interval used for logging and updating of server software.
     * @return time interval in seconds
     */
    public static int getTimeInterval() {
        // Check sanity on time.
        if(timeInterval < 1) {
            return 0;
        }
        else return timeInterval;
    }

    public static String getConfigFile() {
        return logfilePath + logfileName;
    }

    /**
     * Check if system is configured properly, if not, fall back to
     * default values.
     */
    private void checkConfig() {
        if(timeInterval < 1) {
            timeInterval = 60;
        }
        if(logfileName == null) {
            logfileName = "log.txt";
        }
        if(logfilePath == null) {
            logfilePath = System.getProperty("user.home") + "/securitypi/log/";
        }

        createDirectory(logfilePath);
    }

    private void readConfig() {
        try {
            FileReader fr = new FileReader(configFile);

            BufferedReader br = new BufferedReader(fr);

            String line;

            while((line = br.readLine()) != null) {
                if(line.charAt(0) != '#') {
                    if(line.contains("reading.time")) {
                        timeInterval = Integer.parseInt(splitParameters(line));
                    }
                    else if(line.contains("log.path")) {
                        logfilePath = splitParameters(line);
                    }
                    else if(line.contains("log.name")) {
                        logfileName = splitParameters(line);
                    }
                }

            }

            br.close();
            fr.close();
        }
        catch (FileNotFoundException fnf) {
            // This should not happen tho.
            System.out.println("The file was not found.");
        }
        catch (IOException e) {
            // Something wrong with file.
            e.printStackTrace();
        }


    }

    /**
     * Check if a directory exists. If it doesn't exist, create the
     * directory.
     * @return True if directory exists or was created successfully.
     * False if directory could not be created.
     */
    private static boolean createDirectory(String path) {
        File dirPath = new File(path);

        if(!dirPath.exists()) {
            // Directory does not exist, and we must create it.
            try {
                dirPath.mkdirs();
                System.out.println("New path created at " + path);
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

    private String splitParameters(String line) {
        String[] stringParts = line.split("=");
        if(stringParts.length == 2) {
            return stringParts[1];
        }
        else return null;
    }
}
