package org.BaseJava.Model;

public enum ResumeSection {
    PERSONAL("Personal qualities"),
    OBJECTIVE("Life position"),
    ACHIEVEMENT("Achievement"),
    QUALIFICATIONS("QUALIFICATIONS"),
    EXPERIENCE("EXPERIENCE"),
    EDUCATION("EDUCATION");

    private String title;

    ResumeSection(String s) {
        this.title = s;
    }
    public String getTitle() {
        return title;
    }
}
