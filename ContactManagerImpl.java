import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class ContactManagerImpl implements ContactManager {
	
	private Set<Contact> contacts;
	
	public ContactManagerImpl() {
		this.contacts = new HashSet<Contact>();
	}
	
	@Override
	public int addNewContact(String name, String notes) {
		int newContactId = this.contacts.size() + 1;
		Contact newContact = new ContactImpl(newContactId, name, notes);
		contacts.add(newContact);
		return newContactId;
	}
}