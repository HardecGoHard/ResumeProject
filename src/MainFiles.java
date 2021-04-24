import org.Java.Model.ContactType;
import org.Java.Model.Resume;
import org.Java.Model.ResumeSectionType;
import org.Java.Storage.IStorage;
import org.Java.util.Config;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class MainFiles {
    private static IStorage storage = Config.get().getStorage();

    public static void main(String[] args) throws InterruptedException {
        Resume resume = storage.get("UUID1");
        for (ResumeSectionType sectionType : ResumeSectionType.values()) {

            System.out.println(resume.getResumeSection(sectionType).toHtmlEdit(sectionType));

        }
    }

}