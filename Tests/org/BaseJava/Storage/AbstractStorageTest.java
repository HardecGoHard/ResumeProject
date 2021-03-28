package org.BaseJava.Storage;

import org.BaseJava.Exception.NonExsistArrayStorageException;
import org.BaseJava.Model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected AbstractStorage strorage;
    protected static final File directory = new File("C:");

    protected static final Resume RESUME_1;
    protected static final Resume RESUME_2;
    protected static final Resume RESUME_3;
    protected static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume("NAME1");
        RESUME_1.setContact(ContactType.HOME_PAGE, "www.test1.ru");
        RESUME_1.setContact(ContactType.EMAIL, "lightec@mail.ru");
        RESUME_1.setContact(ContactType.GITHUB, "www.github.com/lightec");
        RESUME_1.setContact(ContactType.PHONE, "+79228228228");
        RESUME_1.setResumeSection(ResumeSection.EXPERIENCE, new OrganisationSection(new Organisation("Яндекс",
                "www.yandex.ru", new Organisation.Position(2020, Month.DECEMBER,
                "Стажёр", "Системное программирование C/C++"))));

        RESUME_1.setResumeSection(ResumeSection.ACHIEVEMENT, new ListSection("ачивка1",
                "ачивка2", "ачивка3", "ачивка4"));

        RESUME_1.setResumeSection(ResumeSection.EDUCATION,
                new OrganisationSection(new Organisation("ВШЭ", "www.hse.ru",
                        new Organisation.Position(2016, Month.SEPTEMBER, 2020, Month.SEPTEMBER,
                                "Бакалавариат, ВШЭ", "Учился в ВШЭ на факультете компьютерных наук")),

                        new Organisation("МГУ", "www.msu.ru",
                                new Organisation.Position(2020, Month.SEPTEMBER,
                                        "Магистратура, МГУ", "Обучается в МГУ, ВМК"))
                ));
        RESUME_1.setResumeSection(ResumeSection.PERSONAL, new ListSection("Качество1", "Качество2",
                        "Качество3", "Качество4 "));
        RESUME_1.setResumeSection(ResumeSection.OBJECTIVE, new TextSection("Моя жизненная позиция"));
        RESUME_2 = new Resume("NAME2");
        RESUME_2.setContact(ContactType.HOME_PAGE, "www.test2.ru");
        RESUME_2.setContact(ContactType.EMAIL, "lightec2@mail.ru");
        RESUME_2.setContact(ContactType.GITHUB, "www.github.com/lightec2");
        RESUME_2.setContact(ContactType.PHONE, "+79338338338");
        RESUME_2.setResumeSection(ResumeSection.EXPERIENCE, new OrganisationSection(new Organisation("Google",
                "www.google.com", new Organisation.Position(2020, Month.DECEMBER,
                "Джуниор", "Системное программирование C/C++"))));

        RESUME_2.setResumeSection(ResumeSection.ACHIEVEMENT, new ListSection("ачивка1",
                "ачивка2", "ачивка3", "ачивка4"));

        RESUME_2.setResumeSection(ResumeSection.EDUCATION,
                new OrganisationSection(new Organisation("МАДИ", "www.madi.ru",
                        new Organisation.Position(2016, Month.SEPTEMBER, "Бакалавариат",
                                "Информатика и вычеслительная техника"))
                ));
        RESUME_2.setResumeSection(ResumeSection.PERSONAL, new ListSection("Качество1", "Качество2",
                "Качество3", "Качество4 "));
        RESUME_2.setResumeSection(ResumeSection.OBJECTIVE, new TextSection("Моя жизненная позиция"));

        RESUME_3 = new Resume("NAME3");
        RESUME_3.setContact(ContactType.HOME_PAGE, "www.test3.ru");
        RESUME_3.setContact(ContactType.EMAIL, "lightec3@mail.ru");
        RESUME_3.setContact(ContactType.GITHUB, "www.github.com/lightec3");
        RESUME_3.setContact(ContactType.PHONE, "+79338338338");
        RESUME_3.setResumeSection(ResumeSection.EXPERIENCE, new OrganisationSection(new Organisation("Майл ру",
                "www.mail.ru", new Organisation.Position(2020, Month.DECEMBER,
                "Джуниор", "FrontEnd разработчик"))));

        RESUME_3.setResumeSection(ResumeSection.ACHIEVEMENT, new ListSection("ачивка1",
                "ачивка2", "ачивка3", "ачивка4"));

        RESUME_3.setResumeSection(ResumeSection.EDUCATION,
                new OrganisationSection(new Organisation("МАДИ", "www.madi.ru",
                        new Organisation.Position(2016, Month.SEPTEMBER, "Бакалавариат",
                                "Информационный системы и технологии"))
                ));
        RESUME_3.setResumeSection(ResumeSection.PERSONAL, new ListSection("Качество1", "Качество2",
                "Качество3", "Качество4 "));
        RESUME_3.setResumeSection(ResumeSection.OBJECTIVE, new TextSection("Моя жизненная позиция"));

        RESUME_4 = new Resume("NAME4");
        RESUME_4.setContact(ContactType.HOME_PAGE, "www.test4.ru");
        RESUME_4.setContact(ContactType.EMAIL, "lightec4@mail.ru");
        RESUME_4.setContact(ContactType.GITHUB, "www.github.com/lightec4");
        RESUME_4.setContact(ContactType.PHONE, "+79338338348");
        RESUME_4.setResumeSection(ResumeSection.EXPERIENCE, new OrganisationSection(new Organisation("Майл ру",
                "www.mail.ru", new Organisation.Position(2020, Month.DECEMBER,
                "middle", "BackEnd разработчик"))));

        RESUME_4.setResumeSection(ResumeSection.ACHIEVEMENT, new ListSection("ачивка1",
                "ачивка2", "ачивка3", "ачивка4"));

        RESUME_4.setResumeSection(ResumeSection.EDUCATION,
                new OrganisationSection(new Organisation("МИСиС", "www.misis.ru",
                        new Organisation.Position(2016, Month.SEPTEMBER, "Бакалавариат",
                                "Информатика и вычислительная техника"))
                ));
        RESUME_4.setResumeSection(ResumeSection.PERSONAL, new ListSection("Качество1", "Качество2",
                "Качество3", "Качество4 "));
        RESUME_4.setResumeSection(ResumeSection.OBJECTIVE, new TextSection("Моя жизненная позиция"));
    }


    public AbstractStorageTest(AbstractStorage strorage) {
        this.strorage = strorage;
    }

    @Before
    public void setUp()  {
        strorage.clear();
        strorage.save(RESUME_1);
        strorage.save(RESUME_2);
        strorage.save(RESUME_3);
        strorage.save(RESUME_4);
    }

    @Test
    public void get() {
        assertGet(RESUME_1.getUuid());
        assertGet(RESUME_2.getUuid());
        assertGet(RESUME_3.getUuid());
    }

    @Test(expected = NonExsistArrayStorageException.class)
    public void NotExsistGet() {
        assertGet("PUK");
    }

    @Test(expected = NonExsistArrayStorageException.class)
    public void delete() {
        strorage.delete(RESUME_2.getUuid());
        assertSize(3);
        assertGet(RESUME_2.getUuid());
    }

    @Test
    public void update() {
        Resume resume = new Resume("Test Name");
        strorage.save(resume);
        assertGet(resume.getUuid());
    }

    @Test
    public void clear() {
        strorage.clear();
        assertEquals(0, strorage.getSize());
    }

    @Test
    public void getSize() {
        assertSize(4);
    }

    @Test
    public void getSortedAll() {
        ArrayList<Resume> array = new ArrayList<>();
        array.add(RESUME_1);
        array.add(RESUME_2);
        array.add(RESUME_3);
        array.add(RESUME_4);
        Collections.sort(array);
        assertSize(4);
        assertEquals(array, strorage.getSortedAll());
    }

    protected void assertGet(String s) {
        assertEquals(s, strorage.get(s).getUuid());
    }

    protected void assertSize(int size) {
        assertEquals(size, strorage.getSize());
    }
}