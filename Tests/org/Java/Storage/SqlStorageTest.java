package org.Java.Storage;

import org.Java.Model.*;
import org.Java.util.Config;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SqlStorageTest {
    private static Config config = Config.get();
    private static final SqlStorage SQL_STORAGE = new SqlStorage(config.getDataBaseUrl(),
            config.getDataBaseUser(), config.getDataBasePassword());

    protected static final Resume RESUME_1;
    protected static final Resume RESUME_2;
    protected static final Resume RESUME_3;
    protected static final Resume RESUME_4;
    static {
        RESUME_1 = new Resume("NAME1", "UUID1");
        RESUME_1.setContact(ContactType.HOME_PAGE, "www.test1.ru");
        RESUME_1.setContact(ContactType.EMAIL, "lightec@mail.ru");
        RESUME_1.setContact(ContactType.GITHUB, "www.github.com/lightec");
        RESUME_1.setContact(ContactType.PHONE, "+79228228228");
        RESUME_1.setResumeSection(ResumeSectionType.EXPERIENCE, new OrganisationSection(new Organisation("Яндекс",
                "www.yandex.ru", new Organisation.Position(2020, Month.DECEMBER,
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

        RESUME_2 = new Resume("NAME2", "UUID2");
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

        RESUME_3 = new Resume("NAME3", "UUID3");
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

        RESUME_4 = new Resume("NAME4", "UUID4");
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
    }

    @Before
    public void setUp()  {
        SQL_STORAGE.clear();
        SQL_STORAGE.save(RESUME_1);
        SQL_STORAGE.save(RESUME_2);
        SQL_STORAGE.save(RESUME_3);
        SQL_STORAGE.save(RESUME_4);
        Resume rr = new Resume("Сергей Яровенко", "UUID4");
        rr.setContact(ContactType.HOME_PAGE, "www.test34.ru");
        rr.setContact(ContactType.EMAIL, "lightec4@email.ru");
        rr.setContact(ContactType.GITHUB, "www.github.com/lighteeec4");
        rr.setContact(ContactType.PHONE, "+7933838348");
        SQL_STORAGE.update(rr);
        //assertEquals(SQL_STORAGE.get(rr.getUuid()),rr);
    }
    @Test
    public void update() {
        Resume rr = new Resume("Сергей Яровенко", "UUID4");
        rr.setContact(ContactType.HOME_PAGE, "www.test34.ru");
        rr.setContact(ContactType.EMAIL, "lightec4@email.ru");
        rr.setContact(ContactType.GITHUB, "www.github.com/lighteeec4");
        rr.setContact(ContactType.PHONE, "+7933838348");
         SQL_STORAGE.update(rr);
        assertEquals(SQL_STORAGE.get(rr.getUuid()),rr);
    }

    @Test
    public void get() {
        assertEquals(SQL_STORAGE.get(RESUME_1.getUuid()),RESUME_1);
        assertEquals(SQL_STORAGE.get(RESUME_2.getUuid()),RESUME_2);
        assertEquals(SQL_STORAGE.get(RESUME_3.getUuid()),RESUME_3);
    }

    @Test
    public void delete() {
        SQL_STORAGE.delete(RESUME_1.getUuid());
        assertEquals(3,SQL_STORAGE.getSize());
    }

    @Test
    public void clear() {
        SQL_STORAGE.clear();
        assertEquals(0, SQL_STORAGE.getSize());
    }

    @Test
    public void getSortedAll() {
        List<Resume> list = new ArrayList<>();
        list.add(RESUME_4);
        list.add(RESUME_1);
        list.add(RESUME_3);
        list.add(RESUME_2);
        assertEquals(list,SQL_STORAGE.getSortedAll());
    }

    @Test
    public void getSize() {
        assertEquals(4,SQL_STORAGE.getSize());
    }
}