import java.util.Calendar;
import java.util.Set;

public abstract class MeetingImpl implements Meeting {
	
	private int id;
	private Calendar date;
	private Set<Contact> attendees;
	
	public MeetingImpl(int id, Calendar date, Set<Contact> contacts) {
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