package org.Java.Model;

import org.Java.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;

    private String fullName;
    private String uuid;

    private final Map<ContactType, String> contactEnumMap = new EnumMap<>(ContactType.class);
    private final Map<ResumeSectionType, Section> resumeSectionEnumMap = new EnumMap<>(ResumeSectionType.class);

    public Resume(String fullName) {
        this( fullName,UUID.randomUUID().toString());
    }
    public Resume(){
        this("TempName");
    }

    public Resume( String fullName,String uuid) {
        Objects.requireNonNull(uuid, "Uuid must be not empty");
        Objects.requireNonNull(fullName, "FullName must be not empty");
        this.uuid = uuid;
        this.fullName = fullName;
    }
    public Map<ContactType, String> getContactEnumMap() {
        return contactEnumMap;
    }

    public Map<ResumeSectionType, Section> getResumeSectionEnumMap() {
        return resumeSectionEnumMap;
    }

    public void setContact(ContactType type, String contact){
        contactEnumMap.put(type,contact);
    }
    public void setResumeSection(ResumeSectionType resumeSectionType, Section section){
        resumeSectionEnumMap.put(resumeSectionType,section );
    }
    public String getContact(ContactType type){
        String string = contactEnumMap.get(type);
        return string==null? "": string;
    }
    public Section getResumeSection(ResumeSectionType resumeSectionType){
        Section section= resumeSectionEnumMap.get(resumeSectionType);
        if (section==null) {
            switch (resumeSectionType) {
                case QUALIFICATIONS:
                case OBJECTIVE:
                    section = new TextSection("");
                    break;
                case ACHIEVEMENT:
                case PERSONAL:
                    section = new ListSection("", "", "", "", "", "");
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    section = new OrganisationSection(new Organisation("", "",
                            new Organisation.Position(DateUtil.EMPTY, DateUtil.EMPTY, "", "")));
                    break;
            }
        }
        return section;
    }

    public String getFullName() {
        return fullName;
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
        return "Resume{" +
                "fullName='" + fullName + '\'' +
                ", uuid='" + uuid + '\'' + '}';
    }

    @Override
    public int compareTo(Resume o) {
        int ans = this.getFullName().compareTo(o.getFullName());
        return ans != 0 ? ans : this.getUuid().compareTo(o.getUuid());
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
