package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase{

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);

       sessionFactory =
                new Configuration()
                        .addAnnotatedClass(ContactRecord.class)
                        .addAnnotatedClass(GroupRecord.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }

    static List<GroupData> converList(List<GroupRecord> records){
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)){
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static ContactData convert(ContactRecord record){
        return new ContactData().withId("" + record.id)
                .withTitleParameters(record.firstname, record.lastname, record.address, record.telephonehome, record.email)
                .withMobileWorkSecondary(record.telephonemobile, record.telephonework, record.phone2)
                .withMail2Mail3(record.email2, record.email3);
    }

    private static ContactRecord convert(ContactData data){
        var id = data.id();
        if("".equals(id)){
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.FistName(), data.LastName(), data.Address(), data.TelephoneHome()
        , data.Mail());
    }

    public List<GroupData> getGroupList(){
        return converList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }
}
