package org.BaseJava.Model;

import java.util.ArrayList;
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
}
