package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ContactCreationTests extends TestBase{

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var FistName : List.of("", "FistName")) {
//            for (var LastName : List.of("", "LastName")) {
//                for (var Address : List.of("", "Address")) {
//                    for (var TelephoneHome : List.of("", "TelephoneHome")){
//                        for (var Mail : List.of("", "Mail")){
//                            result.add(new ContactData().withTitleParameters(FistName, LastName,
//                                    Address, TelephoneHome, Mail ));
//                        }
//                    }
//                }
//            }
//        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    @Test
    void canCreateContactWithPhoto(){
        var contact = new ContactData()
                .withFirstNameAndLastName((CommonFunctions.randomString(10)), (CommonFunctions.randomString(10)))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);
    }


    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withEverythingExceptIdFirstNameLastName("",
                "", "", "", "", "", "", "", "",
                "", "", "", ""));
        expectedList.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canCreateContactInGroup(){
        var contact = new ContactData().withTitleParameters((CommonFunctions.randomString(10)), (CommonFunctions.randomString(10)),
                (CommonFunctions.randomString(10)), (CommonFunctions.randomString(10)), (CommonFunctions.randomString(10)));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        ContactData newContactInGroup = null;
        for (ContactData contactInGroup : newRelated) {
            if (contactInGroup.FistName().equals(contact.FistName()) &&
                    contactInGroup.LastName().equals(contact.LastName()) &&
                    contactInGroup.Address().equals(contact.Address()) &&
                    contactInGroup.TelephoneHome().equals(contact.TelephoneHome()) &&
                    contactInGroup.Mail().equals(contact.Mail())) {
                newContactInGroup = contactInGroup;
                break;
            }
        }
        List<ContactData> expectedList = new ArrayList<>(oldRelated);
        expectedList.add(newContactInGroup);
        Comparator<ContactData> compareById = Comparator.comparingInt(c -> Integer.parseInt(c.id()));
        newRelated.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated, expectedList);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

    @Test
    void canAddContactToGroup(){
        var contact = new ContactData().withTitleParameters((CommonFunctions.randomString(10)), (CommonFunctions.randomString(10)),
                (CommonFunctions.randomString(10)), (CommonFunctions.randomString(10)), (CommonFunctions.randomString(10)));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        List<ContactData> allContacts = app.hbm().getContactList();
        var oldRelated = app.hbm().getContactsInGroup(group);
        Optional<ContactData> maxIdContact = allContacts.stream()
                .max(Comparator.comparingInt(c -> Integer.parseInt(c.id())));
        int expectedNewContactId = maxIdContact
                .map(c -> Integer.parseInt(c.id()) + 1)
                .orElse(1);
        var contactToAdd = new ContactData()
                .withId(String.valueOf(expectedNewContactId)) // Устанавливаем предсказанный ID
                .withTitleParameters(
                        (CommonFunctions.randomString(10) + "_Fname"),
                        (CommonFunctions.randomString(10) + "_Lname"),
                        (CommonFunctions.randomString(10) + "_Address"),
                        (CommonFunctions.randomString(10) + "_Phone"),
                        (CommonFunctions.randomString(10) + "_Email")
                );
        app.contacts().addContactToGroup(contactToAdd, group);
        List<ContactData> newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }
}
