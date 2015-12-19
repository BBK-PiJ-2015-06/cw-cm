import org.junit.*;
import static org.junit.Assert.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
}