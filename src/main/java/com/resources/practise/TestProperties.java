package com.resources.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    public static void main(String[] args) throws IOException {

        Properties config = new Properties();
        Properties object = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/resources/properties/config.properties");
        config.load(fis);

        System.out.println(config.getProperty("browser"));

    }

}
