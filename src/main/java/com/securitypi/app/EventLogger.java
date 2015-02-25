package com.securitypi.app;

/**
 * Read data from sensors and write to logfile on given
 * intervals. May also be used to report the same data
 * to the server app. This is regular reading that happens
 * whether or not the motion detection is active. Long
 * intervals might be ok.
 */
public class EventLogger implements Runnable {

    private volatile boolean running = true;
    private int interval;
    private SensorHandler sensorHandler;

    EventLogger(SensorHandler sh) {
        sensorHandler = sh;
        interval = ConfigHandler.getTimeInterval();
    }

    public void terminate() {
        running = false;
    }

    @Override
    public void run() {
        while(running) {
            try {
                double temperature = sensorHandler.getTemperature();
                boolean motion = sensorHandler.getMotion();
                writeToServer(temperature, motion);
                LogModule.addSensorReadingToLog(temperature, motion);
                Thread.sleep(interval*1000);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void writeToServer(double temperature, boolean motion) {
        String serverIP = ConfigHandler.getServerIP();
        String serverPort = ConfigHandler.getServerPort();

        System.out.println("Writing data to " + serverIP + ":" + serverPort + " - Temperature: " + temperature + " Motion: " + motion);
    }
}
