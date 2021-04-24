package org.Java.Model;

import java.util.Arrays;
import java.util.List;

public class OrganisationSection extends Section {
   private static final long serialVersionUID = 1L;

   private List<Organisation> listOfOrganisation;

   public OrganisationSection(Organisation... Organisations) {
      this( Arrays.asList(Organisations));
   }
   public OrganisationSection(List<Organisation> listOfOrganisation) {
      this.listOfOrganisation = listOfOrganisation;
   }

   @Override
   public String toHtml() {
      StringBuilder stringBuilder = new StringBuilder("<ul>");
      for(Organisation org : listOfOrganisation){
         stringBuilder.append("<li>" + org.toHtml() + "</li>\n");
      }
      stringBuilder.append("</ul>");
      return stringBuilder.toString();
   }

   @Override
   public String toHtmlEdit(ResumeSectionType resumeSectionType) {
      StringBuilder stringBuilder = new StringBuilder("<h3>"+ resumeSectionType.getTitle()+":</h3>\n");
      stringBuilder.append("<ul>\n");
      for(Organisation org : listOfOrganisation){
         stringBuilder.append("<li>" + org.toHtmlEdit(resumeSectionType) + "</li>\n");
      }
      stringBuilder.append("</ul>\n");
      return stringBuilder.toString();   }
}
