package org.Java.Model;

import java.util.Objects;

public class TextSection extends Section {
    private static final long serialVersionUID = 1L;

    protected String content;

    public TextSection(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toHtml() {
        return "<p>" + content + "</p>";
    }

    @Override
    public String toHtmlEdit(ResumeSectionType resumeSectionType) {
        return "<p>\n" +
                "<dt><b>"+ resumeSectionType.getTitle()+"</b></dt>\n" +
                " <dd><textarea name='"+resumeSectionType.toString()+"'>" +content+ "</textarea></dd>\n" +
                "</p>";
    }
}
