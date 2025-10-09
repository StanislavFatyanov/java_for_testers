package model;

public record ContactData(String id, String FistName, String MiddleName, String LastName, String NickName,
                          String Company,
                          String Address, String TelephoneHome, String TelephoneMobile, String TelephoneWork,
                          String TelephoneFax, String Mail, String Mail2, String Mail3, String Homepage) {

    public ContactData() {
        this("", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.FistName, this.MiddleName, this.LastName, this.NickName, this.Company, this.Address,
                this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax, this.Mail,
                this.Mail2, this.Mail3, this.Homepage);
    }

    public ContactData withFirstNameAndLastName(String FirstName, String LastName) {
        return new ContactData(this.id, FirstName, this.MiddleName, LastName, this.NickName, this.Company, this.Address,
                this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax, this.Mail,
                this.Mail2, this.Mail3, this.Homepage);
    }

    public ContactData withTitleParameters(String FirstName, String LastName, String Address, String TelephoneHome, String Mail) {
        return new ContactData(this.id, FirstName, this.MiddleName, LastName, this.NickName, this.Company, Address,
                TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax, Mail,
                this.Mail2, this.Mail3, this.Homepage);
    }
}
