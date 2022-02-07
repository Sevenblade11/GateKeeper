package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class ConfigManager {
    private static final String path = System.getenv("LOCALAPPDATA");
    private static final String configPath = path + "\\GateKeeper\\config.properties";
    private static final Logger LOGGER = LogManager.getLogger();

    public static void config(){
        createDirectory();
        createConfig();
    }

    private static void createDirectory(){
        File gateKeeperFile = new File(path + "\\GateKeeper\\");
        if(gateKeeperFile.mkdir())
            LOGGER.info("Created GateKeeper directory.");
        else
            LOGGER.info("GateKeeper directory already made.");

    }

    private static void createConfig(){
        try {
            File userNameFolder = new File(configPath);
            if(userNameFolder.createNewFile())
                LOGGER.info("Created GateKeeper config.");
            else
                LOGGER.info("GateKeeper config already made.");
        } catch (IOException e) {
            LOGGER.error("Error has occurred with creating GateKeeper config: " +e.getMessage());
        }
    }

    public static void writeConfig(String configType, String data){
        try {
            if(!isWritten(configType,data)) {
                LOGGER.info("Attempting to write " + data + " under " + configType + ".");
                Properties props = new Properties();
                File configFile = new File(configPath);
                FileWriter writer = new FileWriter(configFile);
                props.put(configType, data);
                props.store(writer, "Config Settings for GateKeeper");
                writer.close();
                LOGGER.info("Writing to config successful.");
            }
        } catch (IOException e) {
            LOGGER.error("Error has occurred with writing to GateKeeper config: " +e.getMessage());
        }
    }

    private static boolean isWritten(String configType, String data){
        try {
            LOGGER.info("Checking if " +configType + " is already written.");
            Properties props = new Properties();
            FileInputStream reader = new FileInputStream(configPath);
            props.load(reader);
            if(props.contains(configType) && props.getProperty(configType).equals(data))
                return true;
            reader.close();
        } catch (IOException e) {
            LOGGER.error("Error has occurred when checking GateKeeper config: " +e.getMessage());
        }
        return false;
    }

    public static String readConfig(String configType){
        try {
            LOGGER.info("Reading config " +configType);
            Properties props = new Properties();
            FileInputStream reader = new FileInputStream(configPath);
            props.load(reader);
            if(props.containsKey(configType))
                return props.getProperty(configType);
            reader.close();
        } catch (IOException e) {
            LOGGER.error("Error has occurred when checking GateKeeper config: " +e.getMessage());
        }
        return null;
    }

    public static void removeProperty(String configType){
        try {
            LOGGER.info("Removing config " + configType);
            Properties props = new Properties();
            FileInputStream reader = new FileInputStream(configPath);
            FileWriter writer = new FileWriter(configPath);
            props.load(reader);
            props.remove(configType);
            props.store(writer, null);
            reader.close();
            writer.close();
            LOGGER.info("Successfully removed config " + configType);
        } catch (IOException e) {
            LOGGER.error("Error has occurred when checking GateKeeper config: " +e.getMessage());
        }
    }
}
