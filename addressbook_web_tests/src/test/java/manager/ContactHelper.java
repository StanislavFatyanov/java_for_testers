package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactFrom(contact);
        submitContactCreation();
        returnToHomePage();
        try {
            Thread.sleep(300);
        } catch ( java.lang.InterruptedException ie) {
            System.out.println(ie);
        }
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactFrom(ContactData contact) {
        type(By.name("firstname"), contact.FistName());
        type(By.name("middlename"), contact.MiddleName());
        type(By.name("lastname"), contact.LastName());
        type(By.name("nickname"), contact.NickName());
        type(By.name("company"), contact.Company());
        type(By.name("address"), contact.Address());
        type(By.name("home"), contact.TelephoneHome());
        type(By.name("mobile"), contact.TelephoneMobile());
        type(By.name("work"), contact.TelephoneWork());
        type(By.name("fax"), contact.TelephoneFax());
        type(By.name("email"), contact.Mail());
        type(By.name("email2"), contact.Mail2());
        type(By.name("email3"), contact.Mail3());
        type(By.name("homepage"), contact.Homepage());
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    public boolean isContactPresent() {
        openContactsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void removeContact(ContactData contact) {
        selectContact(contact);
        click(By.name("delete"));
        returnToHomePage();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public void openContactsPage() {
        if (!manager.isElementPresent(By.name("searchstring"))) {
            click(By.linkText("home"));
        }
    }

    public int getCount() {
        openContactsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var contactRows = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var row : contactRows) {
            var checkbox = row.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            String titleAttribute = checkbox.getAttribute("title");
            String firstName = "";
            String lastName = "";
            if (titleAttribute != null && titleAttribute.startsWith("Select (") && titleAttribute.endsWith(")")) {
                String fullName = titleAttribute.substring("Select (".length(), titleAttribute.length() - 1);
                String[] nameParts = fullName.split(" ", 2);
                if (nameParts.length > 0) {
                    firstName = nameParts[0];
                }
                if (nameParts.length > 1) {
                    lastName = nameParts[1];
                }
            }
            contacts.add(new ContactData().withId(id).withFirstNameAndLastName(firstName, lastName));
        }
        return contacts;
    }

}
