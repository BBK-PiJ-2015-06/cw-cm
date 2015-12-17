import java.util.Calendar;
import java.util.Set;

/**
 * A FutureMeetingImpl is an implementation of the interface FutureMeeting, 
 * which is defined in section 3.5 of the document for Assignment 3 - Contact Manager.
 *
 * A FutureMeetingImpl extends the abstract class MeetingImpl, but provides no 
 * new methods in addition to those inherited from MeetingImpl. The functinality
 * is therefore exactly as specified in the interface Meeting, enabling 
 * FutureMeetingImpl to act only as a naming class for the purpose of distinguishing
 * between past and future meetings.
 *
 * @author George Shiangoli
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {
	
	/**
	 * The constructor of FutureMeetingImpl accepts three parameters: the ID and date
	 * of the meeting, and the Set of contacts that will attend the meeting.
	 *
	 * This method calls the constructor of the parent class MeetingImpl.
	 *
	 * @param id the unique ID of the meeting
	 * @param date the date at which the meeting will be held
	 * @param contacts a set of non identical contacts that will attend this meeting
	 * @throws IllegalArgumentException if the ID provided is less than 1 and / or the 
	 *         Set of contacts is empty
	 * @throws NullPointerException if any of the parameters are null
	 */
	public FutureMeetingImpl(int id, Calendar date, Set<Contact> contacts) {
		super(id, date, contacts);
	}
}