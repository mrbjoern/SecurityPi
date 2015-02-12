package com.securitypi.server;

/**
 * Main class for the server app. This part of the project
 * recieves notifications from the SecurityPi, reads system
 * state etc. The server is also responsible for updating
 * web interface, sending notifications to mobile app etc.
 */
public class SecurityPiServer {
    // Server and app may communicate over sockets or a
    // REST interface. Communication should go both ways,
    // letting the server read state and sensor info from
    // app, and let the app send notifications to the server.

    public static void main(String[] args) {
        System.out.println("This is the server.");
    }

    // TODO: Implement the server application.
}
