import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A ContactManagerImpl is an implementation of the interface ContactManager, 
 * which is defined in section 3.1 of the document for Assignment 3 - Contact Manager.
 *
 * The class is used to manage a user's personal contacts and meetings, each of which 
 * are stored as a set and a list respectively. ContactManagerImpl also contains two 
 * additional private fields; one for storing the time that an instance of the class 
 * was created and the other to hold the filename of the file that will contain all 
 * saved data from each application session.
 *
 * @author George Shiangoli
 */
public class ContactManagerImpl implements ContactManager {
	
	private Set<Contact> contacts;
	private List<Meeting> meetings;
	private Calendar launchTime;
	private final String FILENAME = "contacts.txt";
	
	/**
	 * ContactManagerImpl contains only one constructor that does not take any parameters
	 * as arguments. The constructor builds the contact manager by populating the contacts
	 * and meetings from a file "contacts.txt" which is located within the same directory as
	 * the application. If this file is not found (and therefore this is the first object of
	 * ContactManagerImpl to be created) the constructor simply initialises the set and list
	 * fields to empty data structures. The launchTime is not obtained from the "contacts.txt"
	 * file but is always initialised at each start up to the current time instance.
	 */
	@SuppressWarnings("unchecked")
	public ContactManagerImpl() {
		File contactsFile = new File("." + File.separator + FILENAME);
		if(contactsFile.exists()) {
			try(ObjectInputStream ois = new ObjectInputStream(
				                         new BufferedInputStream(
				                          new FileInputStream(contactsFile)))) {
				this.contacts = (Set<Contact>)ois.readObject();	
				this.meetings = (List<Meeting>)ois.readObject();
			} catch(IOException ex) {
				ex.printStackTrace();
			} catch(ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		} else {
			this.contacts = new HashSet<Contact>();
			this.meetings = new ArrayList<Meeting>();
		}
		this.launchTime = Calendar.getInstance();
	}
	
	/**
	 * @see ContactManager#addFutureMeeting(Set<Contact>, Calendar)
	 */
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		ContactManagerUtils.nullParamChecker(contacts, date);
		if(date.before(this.launchTime)) {
			throw new IllegalArgumentException(
				"Cannot create future meeting with past date");
		}
		if(!this.contacts.containsAll(contacts)) { //tests whether the contacts parameter is a subset of the known contacts
			throw new IllegalArgumentException(
				"Cannot create future meeting with unknown contact");
		}
		int newMeetingId = this.meetings.size() + 1;
		FutureMeeting newMeeting = new FutureMeetingImpl(newMeetingId, date, contacts);
		this.meetings.add(newMeeting);
		return newMeeting.getId();
	}
	
	/**
	 * @see ContactManager#getPastMeeting(int)
	 */
	@Override
	public PastMeeting getPastMeeting(int id) {
		Meeting output = getMeeting(id);
		if(output != null) {
			if(!(output instanceof PastMeeting)) {
				throw new IllegalStateException(
					"ID provided corresponds to invalid meeting type");
			}
			return (PastMeeting)output;
		} else {
			return null;
		}
	}
	
	/**
	 * @see ContactManager#getFutureMeeting(int)
	 */
	@Override
	public FutureMeeting getFutureMeeting(int id) {
		Meeting output = getMeeting(id);
		if(output != null) {
			if(!(output instanceof FutureMeeting)) {
				throw new IllegalArgumentException(
					"ID provided corresponds to invalid meeting type");
			}
			return (FutureMeeting)output;
		} else {
			return null;
		}
	}
	
	/**
	 * @see ContactManager#getMeeting(int)
	 */
	@Override 
	public Meeting getMeeting(int id) {
		if(id <= 0 || id >this.meetings.size()) {
			return null;
		}
		return this.meetings.get(id - 1);
	}
	
	/**
	 * @see ContactManager#getFutureMeetingList(Contact)
	 */
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		ContactManagerUtils.contactChecker(contact, this.contacts);
		List<Meeting> output = new ArrayList<Meeting>();
		for(Meeting m : this.meetings) {
			if(m instanceof FutureMeeting && m.getContacts().contains(contact)) {
				output.add(m);
			} 
		}
		output.sort(ContactManagerUtils::chronologicalChecker);
		ContactManagerUtils.removeDuplicates(output);
		return output;
	}
	
	/**
	 * @see ContactManager#getMeetingListOn(Calendar)
	 */
	@Override 
	public List<Meeting> getMeetingListOn(Calendar date) {
		ContactManagerUtils.nullParamChecker(date);
		List<Meeting> output = new ArrayList<Meeting>();
		for(Meeting m : this.meetings) {
			Calendar meetingDate = m.getDate();
			//Ensure that comparison is only made between year, month and day
			//of meetingDate and date parameter. Enables Calendar objects of
			//different formates to be compared.
			if(meetingDate.get(Calendar.YEAR) == date.get(Calendar.YEAR) 
			   && meetingDate.get(Calendar.MONTH) == date.get(Calendar.MONTH) 
			   && meetingDate.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)) {
				output.add(m);
			}
		}
		output.sort(ContactManagerUtils::chronologicalChecker);
		ContactManagerUtils.removeDuplicates(output);
		return output;
	}
	
	/**
	 * @see ContactManager#getPastMeetingListFor(Contact)
	 */
	@Override 
	public List<PastMeeting> getPastMeetingListFor(Contact contact) {
		ContactManagerUtils.contactChecker(contact, this.contacts);
		List<PastMeeting> output = new ArrayList<PastMeeting>();
		for(Meeting m : this.meetings) {
			if(m instanceof PastMeeting && m.getContacts().contains(contact)) {
				output.add((PastMeeting)m);
			}
		}
		output.sort(ContactManagerUtils::chronologicalChecker);
		ContactManagerUtils.removeDuplicates(output);
		return output;
	}
	
	/**
	 * @see ContactManager#addNewPastMeeting(Set<Contact>, Calendar, String)
	 */
	@Override 
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
		ContactManagerUtils.nullParamChecker(contacts, date, text);
		if(!this.contacts.containsAll(contacts)) {
			throw new IllegalArgumentException(
				"Cannot create past meeting with unknown contact");
		}
		int newMeetingId = this.meetings.size() + 1;
		PastMeeting newMeeting = new PastMeetingImpl(newMeetingId, date, contacts, text);
		this.meetings.add(newMeeting);
	}
	
	/**
	 * @see ContactManager#addMeetingNotes(int, String)
	 */
	@Override 
	public PastMeeting addMeetingNotes(int id, String text) {
		ContactManagerUtils.nullParamChecker(text);
		Meeting m = getMeeting(id);
		if(m == null) {
			throw new IllegalArgumentException("Meeting ID does not exist");
		}
		PastMeeting output = null;
		if(m instanceof PastMeeting) {
			PastMeeting pm = (PastMeeting)m;
			output = new PastMeetingImpl(id, pm.getDate(), pm.getContacts(), pm.getNotes() + '\n' + text);
		} else {
			if(m.getDate().after(this.launchTime)) {
				throw new IllegalStateException(
					"Cannot add notes to meeting that has not occured yet");
			}
			output = new PastMeetingImpl(id, m.getDate(), m.getContacts(), text);
		}
		this.meetings.set((id - 1), output);
		return output;
	}
	
	/**
	 * @see ContactManager#addNewContact(String, String)
	 */
	@Override
	public int addNewContact(String name, String notes) {
		ContactManagerUtils.nullParamChecker(name, notes);
		if(name.equals("") || notes.equals("")) {
			throw new IllegalArgumentException("Parameters cannot be empty strings");
		}
		int newContactId = this.contacts.size() + 1;
		Contact newContact = new ContactImpl(newContactId, name, notes);
		this.contacts.add(newContact);
		return newContact.getId();
	}
	
	/**
	 * @see ContactManager#getContacts(String)
	 */
	@Override 
	public Set<Contact> getContacts(String name) {
		ContactManagerUtils.nullParamChecker(name);
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
	
	/**
	 * @see ContactManager#getContacts(int...)
	 */
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
				throw new IllegalArgumentException(
					"A provided ID does not correspond to a real contact");
			}
			for(Contact c : contacts) {
				if(i == c.getId()) {
					output.add(c);
				}
			}
		}
		return output;
	}
	
	/**
	 * If a "contacts.txt" file does not exist within the current directory, a 
	 * new empty text file is created.
	 *
	 * @see ContactManager#flush()
	 */
	@Override
	public void flush() {
		File contactsFile = new File("." + File.separator + FILENAME);
		clearFile(contactsFile);
		try(ObjectOutputStream oos = new ObjectOutputStream(
			                           new BufferedOutputStream(
			                             new FileOutputStream(contactsFile)))) {
			oos.writeObject(this.contacts);
			oos.writeObject(this.meetings);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Clears the "contacts.txt" file ready to recieve new data input to ensure
	 * effective overwriting of the stored data.
	 *
	 * If a "contacts.txt" file does not already exist, this method simply creates
	 * a new file to receive the read objects.
	 *
	 * @param file the file that is to be cleared and reset to an empty file
	 */
	private void clearFile(File file) {
		if(file.exists()) {
			try {
				file.delete();
			} catch(SecurityException ex) {
				ex.printStackTrace();
			}
		}
		try {
			file.createNewFile();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * This method has been created purely for testing purposes as it is impossible
	 * to create a future meeting with a past date within the contact manager object.
	 *
	 * This method enables the launchTime of this object to be reset so future meetings
	 * with a date that occured in the past can be present within the contact manager.
	 *
	 * @param date the date that the launchTime of this object will be set to
	 */
	public void setLaunchTime(Calendar date) {
		this.launchTime = date;
	}
}