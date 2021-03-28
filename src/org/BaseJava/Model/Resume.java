package org.BaseJava.Model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume>{
    private String fullName;
    private String uuid;

    private final Map<ContactType, String> contactTypeStringEnumMap = new EnumMap<>(ContactType.class);
    private final Map<ResumeSection, Section> resumeSectionStringEnumMap = new EnumMap<>(ResumeSection.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "Uuid must be not empty");
        Objects.requireNonNull(fullName, "FullName must be not empty");
        this.uuid = uuid;
        this.fullName = fullName;
    }
    public void setContact(ContactType type, String contact){
        contactTypeStringEnumMap.put(type,contact);
    }
    public void setResumeSection(ResumeSection resumeSection, Section section){
        resumeSectionStringEnumMap.put(resumeSection,section );
    }
    public String getContact(ContactType type){
        return contactTypeStringEnumMap.get(type);
    }
    public Section getResumeSection(ResumeSection resumeSection){
        return resumeSectionStringEnumMap.get(resumeSection);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUuid() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Resume resume = (Resume) obj;
        return (resume.getUuid().equals(this.uuid));
    }

    @Override
    public int hashCode() {
        return this.uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }


    @Override
    public int compareTo(Resume o) {
        int ans = this.getFullName().compareTo(o.getFullName());
        return ans != 0 ? ans : this.getUuid().compareTo(o.getUuid());
    }
}
