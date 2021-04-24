package org.Java.Model;

import org.Java.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organisation implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Link HomePage;
    private List<Position> listOfPosition;

    public Organisation(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
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

    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String title;
        private final String description;

        private final LocalDate dateStart;
        private final LocalDate dateEnd;
        private static DateTimeFormatter europeanDateFormatter;

        static {
            String europeanDatePattern = "dd.MM.yyyy";
            europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
        }

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

        public String toHtml() {
            StringBuilder stringBuilder = new StringBuilder("<p>");
            stringBuilder.append("<h4>" + this.title + "</h4>" + "C " + europeanDateFormatter.format(dateStart) + " по "
                    + (dateEnd.equals(DateUtil.NOW) ? "сегодняшний день" : europeanDateFormatter.format(dateEnd)));
            stringBuilder.append("<p>" + this.description + "</p>\n</p>");


            return stringBuilder.toString();
        }

        public String toHtmlEdit(ResumeSectionType resumeSectionType) {
            StringBuilder stringBuilder = new StringBuilder("<p>\n");
            stringBuilder.append("<h3>Должность:</h3>\n");
            stringBuilder.append("<dd><input type='text' name='" + resumeSectionType.toString() + "' size='60'" +
                    " value='" + this.title + "'></dd>\n");
            stringBuilder.append("<h4>Дата начала:</h4>\n");
            stringBuilder.append("<dd><input type='text' name='" + resumeSectionType.toString() + "' size='60'" +
                    " value='" + "232.2323.3232" + "'></dd>\n");
            stringBuilder.append("<h4>Дата конца:</h4>\n");
            if (dateEnd.equals(DateUtil.NOW)) {
                stringBuilder.append("<dd><input type='text' name='" + resumeSectionType.toString() + "' size='60'" +
                        " value='" + europeanDateFormatter.format(LocalDate.now()) + "'></dd>\n");
            } else {
                stringBuilder.append("<dd><input type='text' name='" + resumeSectionType.toString() + "' size='60'" +
                        " value='" + europeanDateFormatter.format(dateEnd) + "'></dd>\n");
            }
            stringBuilder.append("<h4>Описание:</h4>\n");
            stringBuilder.append("<dd><input type='text' name='" + resumeSectionType.toString() + "' size='60'" +
                    " value='" + description + "'></dd></p>\n");
            return stringBuilder.toString();
        }
    }

    public String toHtml() {
        StringBuilder stringBuilder = new StringBuilder("<p>");
        stringBuilder.append("<h4><a href='http://" + HomePage.getUrl() + "'>" + HomePage.getName() + "</a></h4>");
        stringBuilder.append("\n<ul>");
        for (Position pos : listOfPosition) {
            stringBuilder.append("<li>" + pos.toHtml() + "</li>\n");
        }
        stringBuilder.append("\n</ul>\n</p>");
        return stringBuilder.toString();
    }

    public String toHtmlEdit(ResumeSectionType resumeSectionType) {
        StringBuilder stringBuilder = new StringBuilder("<p>");
        stringBuilder.append("<dt><b>Название:</b></dt>\n");
        stringBuilder.append("<dd><input type='text' name='" + resumeSectionType.toString() + "' size='60'" +
                " value='" + HomePage.getName() + "'></dd>\n");
        stringBuilder.append("<dt><b>Ссылка:</b></dt>\n");
        stringBuilder.append("<dd><input type='text' name='" + resumeSectionType.toString() + "' size='60'" +
                " value='" + HomePage.getUrl() + "'></dd>\n");
        for (Position pos : listOfPosition) {
            stringBuilder.append("<li>" + pos.toHtml() + "</li>\n");
        }
        return stringBuilder.toString();
    }
}
