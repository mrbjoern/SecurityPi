package com.securitypi.app;

/**
 * Listens on the sensors from SensorHandler. Logs events at
 * given times. Should only run when the controller is on.
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
                System.out.println("Thread is running.");
                double temperature = sensorHandler.getTemperature();
                boolean motion = sensorHandler.getMotion();
                LogModule.addSensorReadingToLog(temperature, motion);
                Thread.sleep(interval*1000);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
