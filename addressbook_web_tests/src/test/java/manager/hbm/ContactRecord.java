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
    public String email;

    public ContactRecord(){}

    public ContactRecord(int id, String firstname, String lastname, String address, String telephonehome, String email){

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.telephonehome = telephonehome;
        this.email = email;
    }
}
