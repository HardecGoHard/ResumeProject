package org.BaseJava.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrganisationSection extends Section {
   private List<Organisation> listOfOrganisation;

   public OrganisationSection(Organisation... Organisations) {
      this( Arrays.asList(Organisations));
   }
   public OrganisationSection(List<Organisation> listOfOrganisation) {
      this.listOfOrganisation = listOfOrganisation;
   }
}
