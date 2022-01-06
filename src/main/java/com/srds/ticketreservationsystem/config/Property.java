package com.srds.ticketreservationsystem.config;

import com.srds.ticketreservationsystem.exception.PropertyDoesNotExistsException;

import java.io.IOException;
import java.util.Properties;

public class Property {
    private static Properties properties;

    public static String get(String key) {
        load();
        return properties.get(key).toString();
    }

    private static void load() {

        if (properties != null) {
            try {
                properties = new Properties();
                properties.load(Property.class.getClassLoader().getResourceAsStream("com.srds.ticketreservationsystem.config.properties"));
            } catch (IOException exception) {
                throw new PropertyDoesNotExistsException(exception);
            }
        }
    }
}
