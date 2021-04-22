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
}
