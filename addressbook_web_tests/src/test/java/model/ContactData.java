package model;

public record ContactData(String id, String FistName, String MiddleName, String LastName, String NickName,
                          String Company,
                          String Address, String TelephoneHome, String TelephoneMobile, String TelephoneWork,
                          String TelephoneFax, String Mail, String Mail2, String Mail3, String Homepage, String Photo,
                          String secondary) {

    public ContactData() {
        this("", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.FistName, this.MiddleName, this.LastName, this.NickName, this.Company, this.Address,
                this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax, this.Mail,
                this.Mail2, this.Mail3, this.Homepage, this.Photo, this.secondary);
    }

    public ContactData withFirstNameAndLastName(String FirstName, String LastName) {
        return new ContactData(this.id, FirstName, this.MiddleName, LastName, this.NickName, this.Company, this.Address,
                this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax, this.Mail,
                this.Mail2, this.Mail3, this.Homepage, this.Photo, this.secondary);
    }

    public ContactData withTitleParameters(String FirstName, String LastName, String Address, String TelephoneHome, String Mail) {
        return new ContactData(this.id, FirstName, this.MiddleName, LastName, this.NickName, this.Company, Address,
                TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax, Mail,
                this.Mail2, this.Mail3, this.Homepage, this.Photo, this.secondary);
    }

    public ContactData withEverythingExceptIdFirstNameLastName(String MiddleName, String NickName,
                                                           String Company,
                                                           String Address, String TelephoneHome, String TelephoneMobile, String TelephoneWork,
                                                           String TelephoneFax, String Mail, String Mail2, String Mail3, String Homepage, String Photo, String secondary) {
        return new ContactData(this.id, this.FistName, MiddleName, this.LastName, NickName, Company, Address,
                TelephoneHome, TelephoneMobile, TelephoneWork, TelephoneFax, Mail,
                Mail2, Mail3, Homepage, Photo, secondary);
    }

    public ContactData withPhoto(String Photo) {
        return new ContactData(this.id, this.FistName, this.MiddleName, this.LastName, this.NickName, this.Company, this.Address,
                this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax, this.Mail,
                this.Mail2, this.Mail3, this.Homepage, Photo, this.secondary);
    }

    public ContactData withMobileWorkSecondary(String TelephoneMobile, String TelephoneWork, String secondary) {
        return new ContactData(this.id, this.FistName, this.MiddleName, this.LastName, this.NickName, this.Company, this.Address,
                this.TelephoneHome, TelephoneMobile, TelephoneWork, this.TelephoneFax, this.Mail,
                this.Mail2, this.Mail3, this.Homepage, this.Photo, secondary);
    }

    public ContactData withMail2Mail3(String Mail2, String Mail3) {
        return new ContactData(this.id, this.FistName, this.MiddleName, this.LastName, this.NickName, this.Company, this.Address,
                this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax, this.Mail, Mail2, Mail3, this.Homepage, this.Photo, this.secondary);
    }
}
