package org.Java.util;

import org.Java.Model.*;
import org.Java.Storage.IStorage;
import org.Java.Storage.SqlStorage;

import java.io.*;
import java.time.Month;
import java.util.Properties;
import java.util.Random;

public class Config {
    private static final String PROPERTY_DIR = "/prop.properties";
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
            try (InputStream is = Config.class.getResourceAsStream(PROPERTY_DIR)) {
               //  = new Properties();
                PROPERTIES.load(is);
                this.StorageDir = new File(PROPERTIES.getProperty("storage.dir"));
                this.DataBaseUrl = PROPERTIES.getProperty("db.url");
                this.DataBaseUser = PROPERTIES.getProperty("db.user");
                this.DataBasePassword = PROPERTIES.getProperty("db.password");
                storage = new SqlStorage(getDataBaseUrl(),getDataBaseUser(),getDataBasePassword());
                initResumes();
            } catch (IOException e) {
                throw new IllegalStateException("Invalid config file " + PROPERTY_DIR);
            }

    }
    private void initResumes(){
        Resume RESUME_1 = new Resume("NAME1");
        RESUME_1.setContact(ContactType.HOME_PAGE, "www.test1.ru");
        RESUME_1.setContact(ContactType.EMAIL, "lightec@mail.ru");
        RESUME_1.setContact(ContactType.GITHUB, "www.github.com/lightec");
        RESUME_1.setContact(ContactType.PHONE, "+79228228228");
        RESUME_1.setResumeSection(ResumeSectionType.EXPERIENCE, new OrganisationSection(new Organisation("Яндекс",
                "yandex.ru", new Organisation.Position(2020, Month.DECEMBER,
                "Стажёр", "Системное программирование C/C++"))));

        RESUME_1.setResumeSection(ResumeSectionType.ACHIEVEMENT, new ListSection("ачивка1",
                "ачивка2", "ачивка3", "ачивка4"));

        RESUME_1.setResumeSection(ResumeSectionType.EDUCATION,
                new OrganisationSection(new Organisation("ВШЭ", "www.hse.ru",
                        new Organisation.Position(2016, Month.SEPTEMBER, 2020, Month.SEPTEMBER,
                                "Бакалавариат, ВШЭ", "Учился в ВШЭ на факультете компьютерных наук")),

                        new Organisation("МГУ", "www.msu.ru",
                                new Organisation.Position(2020, Month.SEPTEMBER,
                                        "Магистратура, МГУ", "Обучается в МГУ, ВМК"))
                ));
        RESUME_1.setResumeSection(ResumeSectionType.PERSONAL, new ListSection("Качество1", "Качество2",
                "Качество3", "Качество4 "));
        RESUME_1.setResumeSection(ResumeSectionType.OBJECTIVE, new TextSection("Моя жизненная позиция"));

        Resume RESUME_2 = new Resume("NAME2", "UUID");
        RESUME_2.setContact(ContactType.HOME_PAGE, "www.test2.ru");
        RESUME_2.setContact(ContactType.EMAIL, "lightec2@mail.ru");
        RESUME_2.setContact(ContactType.GITHUB, "www.github.com/lightec2");
        RESUME_2.setContact(ContactType.PHONE, "+79338338338");
        RESUME_2.setResumeSection(ResumeSectionType.EXPERIENCE, new OrganisationSection(new Organisation("Google",
                "www.google.com", new Organisation.Position(2020, Month.DECEMBER,
                "Джуниор", "Системное программирование C/C++"))));

        RESUME_2.setResumeSection(ResumeSectionType.ACHIEVEMENT, new ListSection("ачивка1",
                "ачивка2", "ачивка3", "ачивка4"));

        RESUME_2.setResumeSection(ResumeSectionType.EDUCATION,
                new OrganisationSection(new Organisation("МАДИ", "www.madi.ru",
                        new Organisation.Position(2016, Month.SEPTEMBER, "Бакалавариат",
                                "Информатика и вычеслительная техника"))
                ));
        RESUME_2.setResumeSection(ResumeSectionType.PERSONAL, new ListSection("Качество1", "Качество2",
                "Качество3", "Качество4 "));
        RESUME_2.setResumeSection(ResumeSectionType.OBJECTIVE, new TextSection("Моя жизненная позиция"));

        Resume RESUME_3 = new Resume("NAME3");
        RESUME_3.setContact(ContactType.HOME_PAGE, "www.test3.ru");
        RESUME_3.setContact(ContactType.EMAIL, "lightec3@mail.ru");
        RESUME_3.setContact(ContactType.GITHUB, "www.github.com/lightec3");
        RESUME_3.setContact(ContactType.PHONE, "+79338338338");
        RESUME_3.setResumeSection(ResumeSectionType.EXPERIENCE, new OrganisationSection(new Organisation("Майл ру",
                "www.mail.ru", new Organisation.Position(2020, Month.DECEMBER,
                "Джуниор", "FrontEnd разработчик"))));

        RESUME_3.setResumeSection(ResumeSectionType.ACHIEVEMENT, new ListSection("ачивка1",
                "ачивка2", "ачивка3", "ачивка4"));

        RESUME_3.setResumeSection(ResumeSectionType.EDUCATION,
                new OrganisationSection(new Organisation("МАДИ", "www.madi.ru",
                        new Organisation.Position(2016, Month.SEPTEMBER, "Бакалавариат",
                                "Информационный системы и технологии"))
                ));
        RESUME_3.setResumeSection(ResumeSectionType.PERSONAL, new ListSection("Качество1", "Качество2",
                "Качество3", "Качество4 "));
        RESUME_3.setResumeSection(ResumeSectionType.OBJECTIVE, new TextSection("Моя жизненная позиция"));

        Resume RESUME_4 = new Resume("NAME4");
        RESUME_4.setContact(ContactType.HOME_PAGE, "www.test4.ru");
        RESUME_4.setContact(ContactType.EMAIL, "lightec4@mail.ru");
        RESUME_4.setContact(ContactType.GITHUB, "www.github.com/lightec4");
        RESUME_4.setContact(ContactType.PHONE, "+79338338348");
        RESUME_4.setResumeSection(ResumeSectionType.EXPERIENCE, new OrganisationSection(new Organisation("Майл ру",
                "www.mail.ru", new Organisation.Position(2020, Month.DECEMBER,
                "middle", "BackEnd разработчик"))));

        RESUME_4.setResumeSection(ResumeSectionType.ACHIEVEMENT, new ListSection("ачивка1",
                "ачивка2", "ачивка3", "ачивка4"));

        RESUME_4.setResumeSection(ResumeSectionType.EDUCATION,
                new OrganisationSection(new Organisation("МИСиС", "www.misis.ru",
                        new Organisation.Position(2016, Month.SEPTEMBER, "Бакалавариат",
                                "Информатика и вычислительная техника"))
                ));
        RESUME_4.setResumeSection(ResumeSectionType.PERSONAL, new ListSection("Качество1", "Качество2",
                "Качество3", "Качество4 "));
        RESUME_4.setResumeSection(ResumeSectionType.OBJECTIVE, new TextSection("Моя жизненная позиция"));
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);
    }
/*
    private static File getHomeDir(){
        String property = System.getProperty("homeDir");
        File homeDir = new File(property == null ? ".": property);
        return homeDir;
    }

 */
}
