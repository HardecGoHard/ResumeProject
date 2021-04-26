package org.Java.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private static final long serialVersionUID = 1L;

    private List<String> section;

    public ListSection(String... section) {
        this(Arrays.asList(section));
    }
    public ListSection(List<String> section) {
        this.section = section;
    }

    @Override
    public int hashCode() {
        return Objects.hash(section);
    }

    public List<String> getSection() {
        return section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(section, that.section);
    }

    @Override
    public String toHtml() {
        StringBuilder stringBuilder = new StringBuilder("<ul>\n");
        for(String s : section){
            stringBuilder.append("<li>" + s + "</li>\n");
        }
        stringBuilder.append("</ul>\n");
        return stringBuilder.toString();
    }

    @Override
    public String toHtmlEdit(ResumeSectionType resumeSectionType) {
        StringBuilder stringBuilder = new StringBuilder("<dt><b>"+ resumeSectionType.getTitle()+":</b>\n");
        stringBuilder.append("<ul>\n");
        for(String s : section){
            stringBuilder.append("<p><dd><li> <input type='text' name='"+resumeSectionType.toString()+"' size='60'" +
                    " value='"+s+"'>\n </li></dd></p2>\n");
        }
        stringBuilder.append("</ul>\n</dt>");
        return stringBuilder.toString();
    }
}
