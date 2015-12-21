import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestContactManagerGetListMethods {
	
	private ContactManager manager;
	
	@Before
	public void init() {
		manager = new ContactManagerImpl();
		
		//Populating contact list
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
		manager.addNewContact("Steve", "Notes");
		
		//Populating meeting list
		manager.addNewPastMeeting(manager.getContacts(5), new GregorianCalendar(2014, 4, 20), "Notes");
		manager.addFutureMeeting(manager.getContacts(1,2,3), new GregorianCalendar(2017, 0, 1));
		manager.addNewPastMeeting(manager.getContacts(3,5,8), new GregorianCalendar(2014, 5, 5), "Notes");
		manager.addNewPastMeeting(manager.getContacts(1,6,7,10), new GregorianCalendar(2014, 3, 3), "Notes");
		manager.addFutureMeeting(manager.getContacts(4,8), new GregorianCalendar(2017, 0, 22));
		manager.addFutureMeeting(manager.getContacts(1,3,8,10), new GregorianCalendar(2017, 1, 12));
		manager.addFutureMeeting(manager.getContacts(4,5,6,7), new GregorianCalendar(2017, 2, 3));
		manager.addNewPastMeeting(manager.getContacts(5,6,8,9), new GregorianCalendar(2014, 0, 2), "Notes");
		manager.addNewPastMeeting(manager.getContacts(2,3,4), new GregorianCalendar(2014, 11, 12), "Notes");
		manager.addFutureMeeting(manager.getContacts(2,3), new GregorianCalendar(2017, 0, 5));
		manager.addNewPastMeeting(manager.getContacts(1,2,3,4,5,6,7,8,9,10,11), new GregorianCalendar(2014, 9, 11), "Notes");
		manager.addFutureMeeting(manager.getContacts(1,9), new GregorianCalendar(2017, 2, 30));
	}
	
	@Test 
	public void testGetFutureMeetingList() {
		Contact[] contact = manager.getContacts(1).toArray(new Contact[0]);
		List<Meeting> meetingList = manager.getFutureMeetingList(contact[0]);
		assertEquals(3, meetingList.size());
		assertTrue(meetingList.get(0).getDate().before(meetingList.get(1).getDate()));
		assertTrue(meetingList.get(1).getDate().before(meetingList.get(2).getDate()));
		assertEquals(2, meetingList.get(0).getId());
		assertEquals(6, meetingList.get(1).getId());
		assertEquals(12, meetingList.get(2).getId());
	}
	
	@Test 
	public void testGetFutureMeetingListWithEmptyOutput() {
		Contact[] contact = manager.getContacts(11).toArray(new Contact[0]);
		List<Meeting> meetingList = manager.getFutureMeetingList(contact[0]);
		assertEquals(0, meetingList.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetFutureMeetingListWithUnknownContact() {
		List<Meeting> meetingList = manager.getFutureMeetingList(new MockContact());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetFutureMeetingListWithNullContact() {
		List<Meeting> meetingList = manager.getFutureMeetingList(null);
	}
}