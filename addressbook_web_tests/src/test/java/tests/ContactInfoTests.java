package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones(){
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstNameAndLastName("some name", "some surname"));
        }
        var contacts =  app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.TelephoneHome(), contact.TelephoneMobile(), contact.TelephoneFax(), contact.secondary())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);

    }
}
