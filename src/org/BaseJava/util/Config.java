package org.BaseJava.util;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final File PROPERTY_DIR = new File("..\\ResumeBaseProject\\config\\prop.properties");
    private static final Properties PROPERTIES  = new Properties();
    private static final Config INSTANCE = new Config();
    private String DataBaseUrl;
    private File StorageDir;
    private String DataBaseUser;
    private String DataBasePassword;
    public static Config get() {
        return INSTANCE;
    }


    public String getDataBaseUrl() {
        return DataBaseUrl;
    }

    public File getStorageDir() {
        return StorageDir;
    }

    public String getDataBaseUser() {
        return DataBaseUser;
    }

    public String getDataBasePassword() {
        return DataBasePassword;
    }

    private Config(){

            try (InputStream is = new FileInputStream(PROPERTY_DIR)) {
               //  = new Properties();
                PROPERTIES.load(is);
                this.StorageDir = new File(PROPERTIES.getProperty("storage.dir"));
                this.DataBaseUrl = PROPERTIES.getProperty("db.url");
                this.DataBaseUser = PROPERTIES.getProperty("db.user");
                this.DataBasePassword = PROPERTIES.getProperty("db.password");
            } catch (IOException e) {
                throw new IllegalStateException("Invalid config file " + PROPERTY_DIR.getAbsolutePath());
            }
    }
}
