package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactsPage();
        initContactModification(contact);
        fillContactFrom(modifiedContact);
        submitContactModification();
        openContactsPage();
    }

    private void initContactModification(ContactData contact) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("maintable")));
        String locator = String.format("a[href^='edit.php?id=%s']", contact.id());
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
        click(By.cssSelector(locator));
    }

    private void submitContactModification() {
        click(By.name("update"));
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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("maintable")));
        selectContact(contact);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("delete")));
        click(By.name("delete"));
        returnToHomePage();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public void openContactsPage() {
        if (!manager.isElementPresent(By.id("maintable"))) {
            click(By.linkText("home"));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("maintable")));
        }
    }

    public int getCount() {
        openContactsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("maintable")));
        var contacts = new ArrayList<ContactData>();
        var contactRows = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var row : contactRows) {
            var checkbox = row.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            String lastName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
            contacts.add(new ContactData().withId(id).withFirstNameAndLastName(firstName, lastName));
        }
        return contacts;
    }
}
