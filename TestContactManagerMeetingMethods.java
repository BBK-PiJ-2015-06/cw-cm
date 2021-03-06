import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * This testing class is used to test the implementation of the interface
 * ContactManager. More specifically it tests those methods that only 
 * involve adding and retrieving single meetings.
 *
 * @author George Shiangoli
 */
public class TestContactManagerMeetingMethods {
	
	private ContactManager manager;
	
	@Before
	public void init() {
		manager = new ContactManagerImpl();
		manager.addNewContact("Mauricio", "Notes");
		manager.addNewContact("Arsene", "Notes");
		manager.addNewContact("Jose", "Notes");
		manager.addNewContact("Louis", "Notes");
		manager.addNewContact("Claudio", "Notes");
		manager.addNewContact("Jurgen", "Notes");
		manager.addNewContact("Roberto", "Notes");
		manager.addNewContact("Slaven", "Notes");
		manager.addNewContact("Ronald", "Notes");
		manager.addNewContact("Alan", "Notes");
	}
	
	@After
	public void cleanUp() {
		File contactsFile = new File("." + File.separator + "contacts.txt");
		if(contactsFile.exists()) {
			contactsFile.delete();
		}	
	}
	
	@Test 
	public void testAddingFirstFutureMeeting() {
		Calendar date = new GregorianCalendar(2016, 0, 10);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		assertEquals(1, manager.addFutureMeeting(attendees, date));
	}
	
	@Test 
	public void testAddingMultipleFutureMeetings() {
		Calendar date = new GregorianCalendar(2016, 0, 10);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		assertEquals(1, manager.addFutureMeeting(attendees, date));
		date = new GregorianCalendar(2016, 0, 14);
		attendees = manager.getContacts(5,8);
		assertEquals(2, manager.addFutureMeeting(attendees, date));
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddingFutureMeetingWithNullContacts() {
		Calendar date = new GregorianCalendar(2016, 0, 10);
		manager.addFutureMeeting(null, date);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddingFutureMeetingWithNullDate() {
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addFutureMeeting(attendees, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddingFutureMeetingWithNullParams() {
		manager.addFutureMeeting(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddingFutureMeetingWithPastDate() {
		Calendar date = new GregorianCalendar(2015, 11, 10);
		Set<Contact> attendees = manager.getContacts(5,8);
		manager.addFutureMeeting(attendees, date);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddingFutureMeetingWithUnknownContact() {
		Calendar date = new GregorianCalendar(2016, 0, 10);
		Set<Contact> attendees = manager.getContacts(5,8);
		attendees.add(new MockContact());
		manager.addFutureMeeting(attendees, date);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddingFutureMeetingWithEmptyContacts() {
		Calendar date = new GregorianCalendar(2016, 0, 10);
		Set<Contact> attendees = new HashSet<Contact>();
		manager.addFutureMeeting(attendees, date);
	}
	
	@Test 
	public void testAddingPastMeetingAsFirstMeeting() {
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		Calendar date = new GregorianCalendar(2015, 6, 11);
		manager.addNewPastMeeting(attendees, date, "Notes");
		date = new GregorianCalendar(2016, 0, 10);
		attendees = manager.getContacts(3,4);
		assertEquals(2, manager.addFutureMeeting(attendees, date));
	}
	
	@Test 
	public void testAddingMultiplePastMeetings() {
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		Calendar date = new GregorianCalendar(2015, 6, 11);
		manager.addNewPastMeeting(attendees, date, "Notes");
		date = new GregorianCalendar(2016, 0, 10);
		attendees = manager.getContacts(3,4);
		assertEquals(2, manager.addFutureMeeting(attendees, date));
		attendees = manager.getContacts(1,2,3,4);
		date = new GregorianCalendar(2015, 6, 11);
		manager.addNewPastMeeting(attendees, date, "Notes");
		date = new GregorianCalendar(2016, 0, 10);
		attendees = manager.getContacts(3,4);
		assertEquals(4, manager.addFutureMeeting(attendees, date));
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNewPastMeetingWithNullContacts() {
		Calendar date = new GregorianCalendar(2015, 6, 11);
		manager.addNewPastMeeting(null, date, "Notes");
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNewPastMeetingWithNullDate() {
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addNewPastMeeting(attendees, null, "Notes");
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNewPastMeetingWithNullNotes() {
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		Calendar date = new GregorianCalendar(2015, 6, 11);
		manager.addNewPastMeeting(attendees, date, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNewPastMeetingWithNullParams() {
		manager.addNewPastMeeting(null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddNewPastMeetingWithEmptyContacts() {
		Calendar date = new GregorianCalendar(2015, 6, 11);
		Set<Contact> attendees = new HashSet<Contact>();
		manager.addNewPastMeeting(attendees, date, "Notes");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddNewPastMeetingWithUnknownContact() {
		Calendar date = new GregorianCalendar(2015, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		attendees.add(new MockContact());
		manager.addNewPastMeeting(attendees, date, "Notes");
	}
	
	@Test 
	public void testGetPastMeeting() {
		Calendar date = new GregorianCalendar(2015, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addNewPastMeeting(attendees, date, "Notes");
		PastMeeting meeting = manager.getPastMeeting(1);
		assertEquals(1, meeting.getId());
		assertEquals(date, meeting.getDate());
		assertEquals(attendees, meeting.getContacts());
		assertEquals("Notes", meeting.getNotes());
	}
	
	@Test 
	public void testGetPastMeetingWhereNoIdExists() {
		assertEquals(null, manager.getPastMeeting(1));
	}
	
	@Test 
	public void testGetPastMeetingWithZeroId() {
		Calendar date = new GregorianCalendar(2015, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addNewPastMeeting(attendees, date, "Notes");
		assertEquals(null, manager.getPastMeeting(0));
	}
	
	@Test 
	public void testGetPastMeetingWithNegativeId() {
		Calendar date = new GregorianCalendar(2015, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addNewPastMeeting(attendees, date, "Notes");
		assertEquals(null, manager.getPastMeeting(-1));
	}
	
	@Test(expected = IllegalStateException.class)
	public void testGetPastMeetingWithIdOfFutureMeeting() {
		Calendar date = new GregorianCalendar(2016, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addFutureMeeting(attendees, date);
		PastMeeting meeting = manager.getPastMeeting(1);
	}
	
	@Test 
	public void testGetFutureMeeting() {
		Calendar date = new GregorianCalendar(2016, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addFutureMeeting(attendees, date);
		FutureMeeting meeting = manager.getFutureMeeting(1);
		assertEquals(1, meeting.getId());
		assertEquals(date, meeting.getDate());
		assertEquals(attendees, meeting.getContacts());
	}
	
	@Test 
	public void testGetFutureMeetingWhereNoIdExists() {
		assertEquals(null, manager.getFutureMeeting(1));
	}
	
	@Test 
	public void testGetFutureMeetingWithZeroId() {
		Calendar date = new GregorianCalendar(2016, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addFutureMeeting(attendees, date);
		assertEquals(null, manager.getFutureMeeting(0));
	}
	
	@Test 
	public void testGetFutureMeetingWithNegativeId() {
		Calendar date = new GregorianCalendar(2016, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addFutureMeeting(attendees, date);
		assertEquals(null, manager.getFutureMeeting(-1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetFutureMeetingWithIdOfPastMeeting() {
		Calendar date = new GregorianCalendar(2016, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addNewPastMeeting(attendees, date, "Notes");
		FutureMeeting meeting = manager.getFutureMeeting(1);
	}
	
	@Test 
	public void testGetMeetingOnPastMeeting() {
		Calendar futureDate = new GregorianCalendar(2016, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addFutureMeeting(attendees, futureDate);
		Calendar pastDate = new GregorianCalendar(2015, 6, 11);
		manager.addNewPastMeeting(attendees, pastDate, "Notes");
		PastMeeting meeting = (PastMeeting)manager.getMeeting(2);
		assertEquals(2, meeting.getId());
		assertEquals(pastDate, meeting.getDate());
		assertEquals(attendees, meeting.getContacts());
		assertEquals("Notes", meeting.getNotes());
	}
	
	@Test 
	public void testGetMeetingOnFutureMeeting() {
		Calendar futureDate = new GregorianCalendar(2016, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addFutureMeeting(attendees, futureDate);
		Calendar pastDate = new GregorianCalendar(2015, 6, 11);
		manager.addNewPastMeeting(attendees, pastDate, "Notes");
		Meeting meeting = manager.getMeeting(2);
		assertEquals(2, meeting.getId());
		assertEquals(pastDate, meeting.getDate());
		assertEquals(attendees, meeting.getContacts());
	}
	
	@Test 
	public void testGetMeetingWithZeroId() {
		Calendar futureDate = new GregorianCalendar(2016, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addFutureMeeting(attendees, futureDate);
		Calendar pastDate = new GregorianCalendar(2015, 6, 11);
		manager.addNewPastMeeting(attendees, pastDate, "Notes");
		assertEquals(null, manager.getMeeting(0));
	}
	
	@Test 
	public void testGetMeetingWithNegativeId() {
		Calendar futureDate = new GregorianCalendar(2016, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addFutureMeeting(attendees, futureDate);
		Calendar pastDate = new GregorianCalendar(2015, 6, 11);
		manager.addNewPastMeeting(attendees, pastDate, "Notes");
		assertEquals(null, manager.getMeeting(-1));
	}
	
	@Test 
	public void testGetMeetingWithInvalidId() {
		Calendar futureDate = new GregorianCalendar(2016, 6, 11);
		Set<Contact> attendees = manager.getContacts(1,2,3,4);
		manager.addFutureMeeting(attendees, futureDate);
		Calendar pastDate = new GregorianCalendar(2015, 6, 11);
		manager.addNewPastMeeting(attendees, pastDate, "Notes");
		assertEquals(null, manager.getMeeting(3));
	}
}