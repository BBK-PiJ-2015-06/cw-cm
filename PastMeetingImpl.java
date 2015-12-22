import java.util.Calendar;
import java.util.Set;

/**
 * A PastMeetingImpl is an implementation of the interface PastMeeting, 
 * which is defined in section 3.4 of the document for Assignment 3 - Contact Manager.
 *
 * A PastMeetingImpl is a class that extends MeetingImpl and in doing so, inherits 
 * all of the functionality of this abstract class. PastMeetingImpl provides an 
 * additional field 'notes' and one additional method getNotes() enabling objects
 * of this class to store and retrieve notes made about meetings that have already
 * occured.
 *
 * @author George Shiangoli
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
	
	private String notes;
	
	/**
	 * The constructor of PastMeetingImpl accepts four parameters: the ID and date
	 * of the meeting, the Set of contacts that attended the meeting and any notes
	 * that may have been recorded during / after the meeting.
	 *
	 * Each parameter is checked for null values and once it is established that
	 * no null parameters have been passed, each is then checked for validity in turn, 
	 * starting with the ID, followed by date, and finally with contacts. No null values 
	 * can be passed as parameters, the meeting ID must be greater than or equal to 1, 
	 * and the Set of contacts cannot be empty.
	 *
	 * This method calls the constructor of the parent class MeetingImpl.
	 *
	 * @param id the unique ID of the meeting
	 * @param date the date at which the meeting was held
	 * @param contacts a set of non identical contacts that attended this meeting
	 * @param notes the notes made about the meeting
	 * @throws IllegalArgumentException if the ID provided is less than 1 and / or the 
	 *         Set of contacts is empty
	 * @throws NullPointerException if any of the parameters are null
	 */
	public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts, String notes) {
		super(id, date, contacts);
		ContactManagerUtils.nullParamChecker(notes);
		this.notes = notes;
	}
	
	/**
	 *{@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public String getNotes() {
		return this.notes;
	}
}