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
		if(name.equals("") || notes.equals("")) {
			throw new IllegalArgumentException("Parameters cannot be empty strings");
		}
		int newContactId = this.contacts.size() + 1;
		Contact newContact = new ContactImpl(newContactId, name, notes);
		contacts.add(newContact);
		return newContact.getId();
	}
	
	@Override 
	public Set<Contact> getContacts(String name) {
		if(name == null) {
			throw new NullPointerException("Parameter cannot be null");
		}
		if(name.equals("")) {
			return this.contacts;
		} else {
			Set<Contact> output = new HashSet<Contact>();
			for(Contact c : contacts) {
				if(c.getName().contains(name)) {
					output.add(c);
				}
			}
			return output;
		}
	}
	
	@Override
	public Set<Contact> getContacts(int... ids) {
		if(ids.length == 0) {
			throw new IllegalArgumentException("Must provide at least 1 ID");
		}
		Set<Contact> output = new HashSet<Contact>();
		for(int i : ids) {
			if(i <= 0 || i > this.contacts.size()) {
				throw new IllegalArgumentException("A provided ID does not correspond to a real contact");
			}
			for(Contact c : contacts) {
				if(i == c.getId()) {
					output.add(c);
				}
			}
		}
		return output;
	}
}