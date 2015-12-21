import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class ContactManagerImpl implements ContactManager {
	
	private Set<Contact> contacts;
	private List<Meeting> meetings;
	private Calendar launchTime;
	
	public ContactManagerImpl() {
		this.contacts = new HashSet<Contact>();
		this.meetings = new ArrayList<Meeting>();
		this.launchTime = Calendar.getInstance();
	}
	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		int newMeetingId = this.meetings.size() + 1;
		FutureMeeting newMeeting = new FutureMeetingImpl(newMeetingId, date, contacts);
		if(date.before(this.launchTime)) {
			throw new IllegalArgumentException("Cannot create future meeting with past date");
		}
		if(!this.contacts.containsAll(contacts)) { //tests whether the contacts parameter is a subset of this contacts
			throw new IllegalArgumentException("Cannot create future meeting with unknown contact");
		}
		this.meetings.add(newMeeting);
		return newMeeting.getId();
	}
	
	@Override
	public PastMeeting getPastMeeting(int id) {
		return (PastMeeting)ContactManagerUtils.getMeetingOfType(getMeeting(id), "PastMeetingImpl");
	}
	
	@Override
	public FutureMeeting getFutureMeeting(int id) {
		return (FutureMeeting)ContactManagerUtils.getMeetingOfType(getMeeting(id), "FutureMeetingImpl");
	}
	
	@Override 
	public Meeting getMeeting(int id) {
		if(id <= 0 || id >this.meetings.size()) {
			return null;
		}
		return this.meetings.get(id - 1);
	}
	
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		List<Meeting> output = new ArrayList<Meeting>();
		for(Meeting m : this.meetings) {
			if(m instanceof FutureMeeting && m.getContacts().contains(contact)) {
				output.add(m);
			}
		}
		output.sort(ContactManagerUtils::chronologicalChecker);
		return output;
	}
	
	@Override 
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
		int newMeetingId = this.meetings.size() + 1;
		PastMeeting newMeeting = new PastMeetingImpl(newMeetingId, date, contacts, text);
		if(!this.contacts.containsAll(contacts)) {
			throw new IllegalArgumentException("Cannot create past meeting with unknown contact");
		}
		this.meetings.add(newMeeting);
	}
	
	@Override
	public int addNewContact(String name, String notes) {
		if(name.equals("") || notes.equals("")) {
			throw new IllegalArgumentException("Parameters cannot be empty strings");
		}
		int newContactId = this.contacts.size() + 1;
		Contact newContact = new ContactImpl(newContactId, name, notes);
		this.contacts.add(newContact);
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
		
		//loops through ids and adds the correct contacts to the output set
		//whilst also checking each ID for validity. Can split these these
		//two functions into separate 'for' loops for improved clarity, but 
		//these have been combined in order to reduce repetitve code.
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