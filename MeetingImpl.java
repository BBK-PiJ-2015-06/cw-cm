import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

/**
 * A MeetingImpl is an implementation of the interface Meeting, 
 * which is defined in section 3.3 of the document for Assignment 3 - Contact Manager.
 *
 * A MeetingImpl is an abstract class and so objects of type MeetingImpl
 * cannot be created. However this class provides all of the basic functionality
 * that is common between PastMeeting and FutureMeeting, both of which extend
 * this class.
 *
 * @author George Shiangoli
 */
public abstract class MeetingImpl implements Meeting, Serializable {
	
	private int id;
	private Calendar date;
	private Set<Contact> attendees;
	
	/**
	 * The constructor of MeetingImpl accepts three parameters: the ID and date
	 * of the meeting, and the Set of contacts that attend(ed) the meeting.
	 *
	 * Each parameter is checked for null values and once it is established that
	 * no null parameters have been passed, each is then checked for validity in turn, 
	 * starting with the ID, followed by date, and finally with contacts. No null values 
	 * can be passed as parameters, the meeting ID must be greater than or equal to 1, 
	 * and the Set of contacts cannot be empty.
	 *
	 * @param id the unique ID of the meeting being created
	 * @param date the date at which the meeting was or will be held
	 * @param contacts a set of non identical contacts that attend this meeting
	 * @throws IllegalArgumentException if the ID provided is less than 1 and / or the 
	 *         Set of contacts is empty
	 * @throws NullPointerException if any of the parameters are null
	 */
	public MeetingImpl(int id, Calendar date, Set<Contact> contacts) {
		ContactManagerUtils.nullParamChecker(date, contacts);
		if(id < 1) {
			throw new IllegalArgumentException("Meeting ID cannot be less than 1");
		}
		if(contacts.isEmpty()) {
			throw new IllegalArgumentException("Set<Contact> must have at least 1 contact");
		}
		this.id = id;
		this.date = date;
		this.attendees = contacts;
	}
	
	/**
	 *{@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int getId() {
		return this.id;
	}
	
	/**
	 *{@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override 
	public Calendar getDate() {
		return this.date;
	}
	
	/**
	 *{@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override 
	public Set<Contact> getContacts() {
		return this.attendees;
	}
}