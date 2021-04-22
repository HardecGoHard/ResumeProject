package org.Java.Model;

public enum ContactType {

    PHONE("Phone number"),
    EMAIL("Email adress"),
    DISCORD("Discord tag"),
    LINKEDIN("Linkedin link"),
    GITHUB("Github profile link"),
    STACKOVERFLOW("Stackoverflow link"),
    HOME_PAGE("Homepage Link");
   private String title;

   ContactType(String title){
       this.title = title;
   }

    public String getTitle() {
        return title;
    }
}
