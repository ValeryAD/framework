package com.github.valeryad.test;

import com.github.valeryad.service.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest {

    @Test
    public void browserTest(){
        Assert.assertEquals(System.getProperty("browser"), "chrome");
    }

    @Test
    public void browserEnvironment(){
        Assert.assertEquals(System.getProperty("environment"), "qa");
    }

}
