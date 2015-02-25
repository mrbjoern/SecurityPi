package com.securitypi.app;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigHandlerTest {

    @BeforeClass
    public static void setUp() throws Exception {
        new ConfigHandler("/home/mrbjoern/Workspace/Java/SecurityPi/src/test/resources/config/config.txt");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetTimeInterval() throws Exception {

    }

    @Test
    public void testGetLogFile() throws Exception {

    }

    @Test
    public void testGetMaxsize() throws Exception {

    }

    @Test
    public void testGetServerIP() throws Exception {

    }

    @Test
    public void testGetServerPort() throws Exception {

    }
}