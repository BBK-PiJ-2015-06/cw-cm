import java.util.Calendar;
import java.util.Set;

public abstract class MeetingImpl implements Meeting {
	
	private int id;
	
	public MeetingImpl(int id, Calendar date, Set<Contact> contacts) {
		this.id = id;
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	
	@Override 
	public Calendar getDate() {
		return null;
	}
	
	@Override 
	public Set<Contact> getContacts() {
		return null;
	}
}