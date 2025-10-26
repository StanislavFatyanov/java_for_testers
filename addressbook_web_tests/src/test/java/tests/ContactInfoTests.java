package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testInfoContact(){
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstNameAndLastName("some name", "some surname"));
        }
        var contacts =  app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var address = app.contacts().getAddress(contact);
        var emails = app.contacts().getEmails(contact);
        var expectedPhones = Stream.of(contact.TelephoneHome(), contact.TelephoneMobile(), contact.TelephoneWork(), contact.secondary())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        var expectedAddress = Stream.of(contact.Address())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        var expectedEmails = Stream.of(contact.Mail(), contact.Mail2(), contact.Mail3())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedPhones, phones);
        Assertions.assertEquals(expectedAddress, address);
        Assertions.assertEquals(expectedEmails, emails);
    }
}
