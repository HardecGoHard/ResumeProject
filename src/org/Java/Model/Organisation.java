package org.Java.Model;

import org.Java.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organisation implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Link HomePage;
    private List<Position> listOfPosition;

    public Organisation(String name, String url, Position... positions) {
        this(new Link(name, url),  Arrays.asList(positions));
    }

    public Organisation(Link link, List<Position> listOfPosition) {
        this.HomePage = link;
        this.listOfPosition = listOfPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organisation that = (Organisation) o;
        return Objects.equals(HomePage, that.HomePage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(HomePage);
    }

    public static class Position implements Serializable
    {
        private static final long serialVersionUID = 1L;

        private final String title;
        private final String description;

        private final LocalDate dateStart;
        private final LocalDate dateEnd;

        public Position(int yearStart, Month monthStart, String title, String description) {
            this(DateUtil.of(yearStart, monthStart), DateUtil.NOW, title, description);
        }

        public Position(int yearStart, Month monthStart, int yearEnd, Month monthEnd, String title, String description) {
            this(DateUtil.of(yearStart, monthStart), DateUtil.of(yearEnd, monthEnd), title, description);
        }

        public Position(LocalDate dateStart, LocalDate dateEnd, String title, String description) {
            Objects.requireNonNull(title, "Tittle can't be null");
            Objects.requireNonNull(dateStart, "dateStart can't be null");
            Objects.requireNonNull(dateEnd, "dateEnd can't be null");
            this.title = title;
            this.description = description;
            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return title.equals(position.title) &&
                    description.equals(position.description) &&
                    dateStart.equals(position.dateStart) &&
                    dateEnd.equals(position.dateEnd);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, description, dateStart, dateEnd);
        }
    }
}
