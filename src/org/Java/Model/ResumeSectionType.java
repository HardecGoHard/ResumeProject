package org.Java.Model;

public enum ResumeSectionType {
    PERSONAL("Personal qualities"),
    OBJECTIVE("Life position"),
    ACHIEVEMENT("Achievement"),
    QUALIFICATIONS("QUALIFICATIONS"),
    EXPERIENCE("EXPERIENCE"),
    EDUCATION("EDUCATION");

    private String title;

    ResumeSectionType(String s) {
        this.title = s;
    }
    public String getTitle() {
        return title;
    }
}
