package org.Java.util;

import org.Java.Model.*;
import org.junit.Test;

import java.time.Month;
import java.util.Random;

import static org.junit.Assert.*;

public class ParserJSONTest {
    protected static final Resume RESUME_1;
static {
    RESUME_1 = new Resume("NAME1", "UUID" + new Random().nextInt(50));
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
}
    @Test
    public void readAndWrite() {
        String js = ParserJSON.write(Resume.class, RESUME_1);
        assertEquals(RESUME_1, ParserJSON.reader(js,Resume.class));
        Section textSection = new TextSection("UnitTests");
        String json = ParserJSON.write(Section.class,textSection);
        assertEquals(textSection, ParserJSON.reader(json,Section.class));
    }

}