package org.Java.Model;

import java.io.Serializable;

public abstract class Section implements Serializable {
    private static final long serialVersionUID = 1L;

    //Методы для вывода полей класса Resume в JSP в html формате
    public abstract String toHtml();
    public abstract String toHtmlEdit(ResumeSectionType resumeSectionType);
}
