package com.securitypi.app;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the default behaviour when empty config file is provided.
 */
public class ConfigHandlerDefaultTest {

    @BeforeClass
    public static void setUp() throws Exception {
        new ConfigHandler("/home/mrbjoern/Workspace/Java/SecurityPi/src/test/resources/config/emptyconfig.txt");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetTimeInterval() throws Exception {
        // Default time interval should be 30 minutes (1800 seconds)
        int expected = 1800;
        assertEquals(expected, ConfigHandler.getTimeInterval());
    }

    @Test
    public void testGetLogFile() throws Exception {
        // Default logfile should be /home/USER/securitypi/log/log.txt
        String expected = System.getProperty("user.home") + "/securitypi/log/" + "log.txt";
        assertEquals(expected, ConfigHandler.getLogFile());
    }

    @Test
    public void testGetMaxsize() throws Exception {
        // Default max size of log file should be 2048kB
        int expected = 2048;
        assertEquals(expected, ConfigHandler.getMaxsize());
    }

    @Test
    public void testGetServerIP() throws Exception {
        // Default should be 127.0.0.1
        String expected = "127.0.0.1";
        assertEquals(expected, ConfigHandler.getServerIP());
    }

    @Test
    public void testGetServerPort() throws Exception {
        // Default should be 4121
        String expected = "4121";
        assertEquals(expected, ConfigHandler.getServerPort());
    }
}