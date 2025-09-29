package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

      @Test
    public void canCreateContactWithNameAndSurname(){
          app.contacts().createContact(new ContactData().withFirstNameAndLastName("some name", "some surname"));
      }

      @Test
    public void canCreateContactWithTitleParameters(){
          app.contacts().createContact(new ContactData().withTitleParameters("some name", "some surname", "some address",
                  "some_phone_number", "some email"));
      }
}
