package org.Java.Model;

public enum ResumeSectionType {
    PERSONAL("Персональные качества"),
    OBJECTIVE("Жизненная позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;

    ResumeSectionType(String s) {
        this.title = s;
    }
    public String getTitle() {
        return title;
    }
}
