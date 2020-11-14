package dev.tuxjsql.core;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ConfigurationLoader {
    public static Configuration createConfigurationFromConfig(Properties properties) {
        return createConfigurationFromConfig(properties, Configuration.class.getClassLoader());
    }

    public static Configuration createConfigurationFromConfig(Properties properties, ClassLoader classLoader) {
        if (properties.containsKey("db.type")) {
            //TODO load VIA Legacy
            return null;
        } else if (properties.containsKey("db.configuration")) {

            String sqlConfiguration = properties.getProperty("db.configuration");
            Configuration config = createConfigClass(sqlConfiguration, classLoader);
            config.loadFromProperties(properties);
            return config;
        } else {
            throw new IllegalArgumentException("Invalid Config");
        }
    }

    private static Configuration createConfigClass(String sqlConfiguration, ClassLoader classLoader) {
        try {
            return (Configuration) Class.forName(sqlConfiguration, true, classLoader).getConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
