import java.util.Calendar;
import java.util.Set;

public abstract class MeetingImpl implements Meeting {
	
	private int id;
	private Calendar date;
	private Set<Contact> attendees;
	
	public MeetingImpl(int id, Calendar date, Set<Contact> contacts) {
		if(date == null || contacts == null) {
			throw new NullPointerException("Parameters cannot be null");
		}
		if(contacts.isEmpty()) {
			throw new IllegalArgumentException("Set<Contact> must have at least 1 contact");
		}
		if(id <= 0) {
			throw new IllegalArgumentException("Meeting ID cannot be less than 1");
		}
		this.id = id;
		this.date = date;
		this.attendees = contacts;
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	
	@Override 
	public Calendar getDate() {
		return this.date;
	}
	
	@Override 
	public Set<Contact> getContacts() {
		return this.attendees;
	}
}