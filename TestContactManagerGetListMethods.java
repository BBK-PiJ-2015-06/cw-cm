import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestContactManagerGetListMethods {
	
	private ContactManagerImpl manager;
	
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
		manager.addNewContact("Sam", "Notes");
		
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
		manager.addNewPastMeeting(manager.getContacts(1,2,3,4,5,6,7,8,9,10), new GregorianCalendar(2014, 9, 11), "Notes");
		manager.addFutureMeeting(manager.getContacts(1,9), new GregorianCalendar(2017, 2, 30));
		manager.addNewPastMeeting(manager.getContacts(11), new GregorianCalendar(2014, 9, 11), "Notes");
		manager.addFutureMeeting(manager.getContacts(4,8), new GregorianCalendar(2017, 0, 22));
		manager.addNewPastMeeting(manager.getContacts(5,6,8,9), new GregorianCalendar(2014, 0, 2), "Notes");
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
	
	@Test 
	public void testGetMeetingList() {
		List<Meeting> meetingList = manager.getMeetingListOn(new GregorianCalendar(2014, 9, 11));
		assertEquals(2, meetingList.size());
	}
	
	@Test 
	public void testGetMeetingList2() {
		List<Meeting> meetingList = manager.getMeetingListOn(new GregorianCalendar(2017, 2, 30));
		assertEquals(1, meetingList.size());
		assertEquals(12, meetingList.get(0).getId());
	}
	
	@Test 
	public void testGetMeetingListOnFreeDate() {
		List<Meeting> meetingList = manager.getMeetingListOn(new GregorianCalendar(2014, 9, 10));
		assertEquals(0, meetingList.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetMeetingListOnNullDate() {
		List<Meeting> meetingList = manager.getMeetingListOn(null);
	}
	
	@Test 
	public void testGetPastMeetingListForValidContact() {
		Contact[] contact = manager.getContacts(3).toArray(new Contact[0]);
		List<PastMeeting> meetingList = manager.getPastMeetingListFor(contact[0]);
		assertEquals(3, meetingList.size());
		assertTrue(meetingList.get(0).getDate().before(meetingList.get(1).getDate()));
		assertTrue(meetingList.get(1).getDate().before(meetingList.get(2).getDate()));
		assertEquals(3, meetingList.get(0).getId());
		assertEquals(11, meetingList.get(1).getId());
		assertEquals(9, meetingList.get(2).getId());
	}
	
	@Test
	public void testGetPastMeetingListForInactiveContact() {
		Contact[] contact = manager.getContacts(12).toArray(new Contact[0]);
		List<PastMeeting> meetingList = manager.getPastMeetingListFor(contact[0]);
		assertEquals(0, meetingList.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetPastMeetingListForUnknownContact() {
		List<PastMeeting> meetingList = manager.getPastMeetingListFor(new MockContact());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetPastMeetingListForNullContact() {
		List<PastMeeting> meetingList = manager.getPastMeetingListFor(null);
	}
	
	@Test 
	public void testAddingNotesToPastMeeting() {
		assertEquals("Notes\nMore notes", manager.addMeetingNotes(1, "More notes").getNotes());
	}
	
	@Test 
	public void testAddingNotesToPastMeetingAndEnsureMeetingIsUpdated() {
		PastMeeting meeting = manager.addMeetingNotes(1, "More notes");
		assertEquals("Notes\nMore notes", manager.getPastMeeting(1).getNotes());
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddMeetingNotesWithNullParam() {
		manager.addMeetingNotes(3, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddMeetingNotesWithIdThatDoesNotExist() {
		manager.addMeetingNotes(0, "More notes");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddMeetingNotesWithIdThatDoesNotExist2() {
		manager.addMeetingNotes(16, "More notes");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddMeetingNotesWithIdThatDoesNotExist3() {
		manager.addMeetingNotes(-2, "More notes");
	}
	
	@Test(expected = IllegalStateException.class)
	public void testAddMeetingNotesToFutureMeetingThatHasNotYetOccured() {
		manager.addMeetingNotes(2, "More notes");
	}
	
	@Test
	public void testAddMeetingNotesToFutureMeetingThatHasOccured() {
		manager.setLaunchTime(new GregorianCalendar(2017, 0, 1));
		manager.addMeetingNotes(2, "More notes");
		assertEquals("More notes", manager.getPastMeeting(2).getNotes());
	}
	
	@Test 
	public void testGetFutureMeetingListWithDuplicateMeetings() {
		Contact[] contact = manager.getContacts(4).toArray(new Contact[0]);
		List<Meeting> meetingList = manager.getFutureMeetingList(contact[0]);
		assertEquals(2, meetingList.size());
		assertTrue(meetingList.get(0).getDate().before(meetingList.get(1).getDate()));
		assertEquals(5, meetingList.get(0).getId());
		assertEquals(7, meetingList.get(1).getId());
	}
	
	@Test 
	public void testGetPastMeetingListForWithDuplicateMeetings() {
		Contact[] contact = manager.getContacts(5).toArray(new Contact[0]);
		List<PastMeeting> meetingList = manager.getPastMeetingListFor(contact[0]);
		assertEquals(4, meetingList.size());
		assertTrue(meetingList.get(0).getDate().before(meetingList.get(1).getDate()));
		assertTrue(meetingList.get(1).getDate().before(meetingList.get(2).getDate()));
		assertTrue(meetingList.get(2).getDate().before(meetingList.get(3).getDate()));
		assertEquals(8, meetingList.get(0).getId());
		assertEquals(1, meetingList.get(1).getId()); 
		assertEquals(3, meetingList.get(2).getId()); 
		assertEquals(11, meetingList.get(3).getId()); 
 	}
}