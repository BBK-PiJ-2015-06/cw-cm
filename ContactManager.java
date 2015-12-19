import java.util.Calendar;
import java.util.List;
import java.util.Set;
/**
* A class to manage your contacts and meetings.
*/
public interface ContactManager {
	/**
	* Add a new meeting to be held in the future.
	*
	* An ID is returned when the meeting is put into the system. This
	* ID must be positive and non-zero.
	*
	* @param contacts a list of contacts that will participate in the meeting
	* @param date the date on which the meeting will take place
	* @return the ID for the meeting
	* @throws IllegalArgumentException if the meeting is set for a time
	*         in the past, of if any contact is unknown / non-existent.
	* @throws NullPointerException if the meeting or the date are null
	*/
	int addFutureMeeting(Set<Contact> contacts, Calendar date);
	
	/**
	* Returns the PAST meeting with the requested ID, or null if there is none.
	*
	* The meeting must have happened at a past date.
	*
	* @param id the ID for the meeting
	* @return the meeting with the requested ID, or null if there is none.
	* @throws IllegalStateException if there is a meeting with that ID happening
	*         in the future
	*/
	PastMeeting getPastMeeting(int id);
	
	/**
	* Create a new record for a meeting that took place in the past.
	*
	* @param contacts a list of participants
	* @param date the date on which the meeting took place
	* @param text messages to be added about the meeting.
	* @throws IllegalArgumentException if the list of contacts is
	*         empty, or any of the contacts does not exist
	* @throws NullPointerException if any of the arguments is null
	*/
	void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text);
	
	/**
	* Create a new contact with the specified name and notes.
	*
	* @param name the name of the contact.
	* @param notes notes to be added about the contact.
	* @return the ID for the new contact
	* @throws IllegalArgumentException if the name or the notes are empty strings
	* @throws NullPointerException if the name or the notes are null
	*/
	int addNewContact(String name, String notes);
	
	/**
	* Returns a list with the contacts whose name contains that string.
	*
	* If the string is the empty string, this methods returns the set
	* that contains all current contacts.
	*
	* @param name the string to search for
	* @return a list with the contacts whose name contains that string.
	* @throws NullPointerException if the parameter is null
	*/
	Set<Contact> getContacts(String name);
	
	/**
	* Returns a list containing the contacts that correspond to the IDs.
	* Note that this method can be used to retrieve just one contact by passing only one ID.
	*
	* @param ids an arbitrary number of contact IDs
	* @return a list containing the contacts that correspond to the IDs.
	* @throws IllegalArgumentException if no IDs are provided or if
	*         any of the provided IDs does not correspond to a real contact
	*/
	Set<Contact> getContacts(int... ids);
}
