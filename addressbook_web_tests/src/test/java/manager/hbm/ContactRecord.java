package manager.hbm;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String address;
    @Column(name = "home")
    public String telephonehome;
    @Column(name = "mobile")
    public String telephonemobile;
    @Column(name = "work")
    public String telephonework;
    public String email;
    public String email2;
    public String email3;
    public String phone2;

    public ContactRecord(){}

    public ContactRecord(int id, String firstname, String lastname, String address, String telephonehome, String email){

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.telephonehome = telephonehome;
        this.telephonemobile = telephonemobile;
        this.telephonework = telephonework;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
    }
}
