package org.Java.util;

import org.Java.Model.ContactType;
import org.Java.Model.Resume;
import org.Java.Storage.IStorage;
import org.Java.Storage.SqlStorage;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final File PROPERTY_DIR = new File(getHomeDir(),"\\config\\prop.properties");
    private static final Properties PROPERTIES  = new Properties();
    private static final Config INSTANCE = new Config();
    private String DataBaseUrl;
    private File StorageDir;
    private String DataBaseUser;
    private String DataBasePassword;
    private SqlStorage storage;
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

    public IStorage getStorage() {
        return storage;
    }


    private Config(){
            try (InputStream is = new FileInputStream(PROPERTY_DIR)) {
               //  = new Properties();
                PROPERTIES.load(is);
                this.StorageDir = new File(PROPERTIES.getProperty("storage.dir"));
                this.DataBaseUrl = PROPERTIES.getProperty("db.url");
                this.DataBaseUser = PROPERTIES.getProperty("db.user");
                this.DataBasePassword = PROPERTIES.getProperty("db.password");
                storage = new SqlStorage(getDataBaseUrl(),getDataBaseUser(),getDataBasePassword());
                Resume RESUME_1 = new Resume("NAME1", "UUID1");
                RESUME_1.setContact(ContactType.HOME_PAGE, "www.test1.ru");
                RESUME_1.setContact(ContactType.EMAIL, "lightec@mail.ru");
                RESUME_1.setContact(ContactType.GITHUB, "www.github.com/lightec");
                RESUME_1.setContact(ContactType.PHONE, "+79228228228");
                storage.save(RESUME_1);
            } catch (IOException e) {
                throw new IllegalStateException("Invalid config file " + PROPERTY_DIR.getAbsolutePath());
            }

    }

    private static File getHomeDir(){
        String property = System.getProperty("homeDir");
        File homeDir = new File(property == null ? ".": property);
        return homeDir;
    }
}
