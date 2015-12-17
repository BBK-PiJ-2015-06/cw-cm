import java.util.Calendar;
import java.util.Set;

public abstract class MeetingImpl implements Meeting {
	
	public MeetingImpl(int id, Calendar date, Set<Contact> contacts) {}
	
	@Override
	public int getId() {
		return -1;
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