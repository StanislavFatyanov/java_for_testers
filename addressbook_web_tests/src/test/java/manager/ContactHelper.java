package manager;

import model.ContactData;
import org.openqa.selenium.By;

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

    public void removeContact() {
        selectContact();
        click(By.name("delete"));
        returnToHomePage();
    }

    private void selectContact() {
        click(By.name("selected[]"));
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
}
