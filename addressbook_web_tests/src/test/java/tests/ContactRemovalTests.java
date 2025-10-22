package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstNameAndLastName("some name", "some surname"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemoveContactFromGroup() {
        var contact = new ContactData().withTitleParameters((CommonFunctions.randomString(10)), (CommonFunctions.randomString(10)),
                (CommonFunctions.randomString(10)), (CommonFunctions.randomString(10)), (CommonFunctions.randomString(10)));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        ContactData contactWithCorrectId = null;
        for (ContactData contactInList : newRelated) {
            if (contactInList.FistName().equals(contact.FistName()) &&
                    contactInList.LastName().equals(contact.LastName()) &&
                    contactInList.Address().equals(contact.Address()) &&
                    contactInList.TelephoneHome().equals(contact.TelephoneHome()) &&
                    contactInList.Mail().equals(contact.Mail())) {
                contactWithCorrectId = contactInList;
                break;
            }
        }
        String idToRemove = contactWithCorrectId.id();
        app.contacts().removeContactFromGroup(contactWithCorrectId, group);
        List<ContactData> contactsInGroupAfterRemoval = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(newRelated.size() - 1, contactsInGroupAfterRemoval.size());
    }
}
