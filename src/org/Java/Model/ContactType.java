package org.Java.Model;

public enum ContactType {

    PHONE("Телефон"){
        public String toHtml(String value) {
            return value + "</br>";
        }
    },
    EMAIL("Email"){
        @Override
        public String toHtml(String value) {
            return "<a href ='mailto:" +value+"'> " + value + "</a> <br>" ;
        }
    },
    DISCORD("Дискорд"),
    LINKEDIN("Linkedin"),
    GITHUB("Github"),
    STACKOVERFLOW("Stackoverflow"),
    HOME_PAGE("Homepage");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String toHtml(String value) {
        return "<a href =http://" +value+"'> " + value + "</a> <br>";
    }
}
