package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase{

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var FistName : List.of("", "FistName")) {
            for (var LastName : List.of("", "LastName")) {
                for (var Address : List.of("", "Address")) {
                    for (var TelephoneHome : List.of("", "TelephoneHome")){
                        for (var Mail : List.of("", "Mail")){
                            result.add(new ContactData(FistName, "", LastName, "", "", Address, TelephoneHome,
                                    "", "", "", Mail, "", "", ""));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData(randomString(i * 10), "", randomString(i * 10), "", "",
                    randomString(i * 10), randomString(i * 10), "", "", "", randomString(i * 10), "", "", "" ));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
}
