package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var FistName : List.of("", "FistName")) {
            for (var LastName : List.of("", "LastName")) {
                for (var Address : List.of("", "Address")) {
                    for (var TelephoneHome : List.of("", "TelephoneHome")){
                        for (var Mail : List.of("", "Mail")){
                            result.add(new ContactData().withTitleParameters(FistName, LastName,
                                    Address, TelephoneHome, Mail ));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData().withTitleParameters((randomString(i * 10)), (randomString(i * 10)),
                    (randomString(i * 10)), (randomString(i * 10)), (randomString(i * 10))));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withEverythingExceptIdFirstNameLastName("",
                "", "", "", "", "", "", "", "",
                "", "", ""));
        expectedList.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
