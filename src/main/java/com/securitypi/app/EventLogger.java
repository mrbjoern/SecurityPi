package com.securitypi.app;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
    private ComponentHandler componentHandler;

    EventLogger(ComponentHandler sh) {
        componentHandler = sh;
        interval = ConfigHandler.getTimeInterval();
    }

    public void terminate() {
        running = false;
    }

    @Override
    public void run() {
        while(running) {
            try {
                double temperature = componentHandler.getTemperature();
                boolean motion = componentHandler.getMotion();
                writeToServer(temperature, motion);
                LogModule.addSensorReadingToLog(temperature, motion);

                Client client = Client.create();
                WebResource webResource = client.resource("http://192.168.0.1:8080/api/report/temperature");

                String input = "{\"temperature\": " + temperature +",\"timestamp\":\"N/A\"}";

                ClientResponse clientResponse = webResource.type("application/json").post(ClientResponse.class, input);

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
