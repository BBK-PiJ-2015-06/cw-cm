import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This testing class is used to test the implementation of the interface
 * ContactManager. More specifically it tests the flush() method and the 
 * constructor of ContactManagerImpl and their ability to store and retireve
 * date when closing / loading the contact manager application.
 *
 * @author George Shiangoli
 */
public class TestContactManagerFlush {
	
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
	}
	
	@After
	public void cleanUp() {
		File contactsFile = new File("." + File.separator + "contacts.txt");
		if(contactsFile.exists()) {
			contactsFile.delete();
		}	
	}
	
	@Test 
	public void testFlushCreatesTxtFile() {
		manager.flush();
		assertTrue(new File("." + File.separator + "contacts.txt").exists());
	}
	
	@Test 
	public void testFlushStoresAllContacts() {
		manager.flush();
		ContactManager manager2 = new ContactManagerImpl();
		assertEquals(12, manager2.getContacts("").size());
	}
	
	@Test 
	public void testFlushStoresAllMeetings() {
		manager.flush();
		ContactManager manager2 = new ContactManagerImpl();
		assertEquals(14, manager2.addFutureMeeting(manager2.getContacts(5,6,7), new GregorianCalendar(2017, 11, 2)));
	}
	
	@Test 
	public void testFlushStoresAllContactsAndOverwrites() {
		manager.flush();
		ContactManager manager2 = new ContactManagerImpl();
		assertEquals(12, manager2.getContacts("").size());
		manager2.addNewContact("Mark", "notes");
		manager2.flush();
		ContactManager manager3 = new ContactManagerImpl();
		assertEquals(13, manager3.getContacts("").size());
		assertEquals("Mauricio", getContactName(manager3.getContacts(1)));
		assertEquals("Arsene", getContactName(manager3.getContacts(2)));
		assertEquals("Jose", getContactName(manager3.getContacts(3)));
		assertEquals("Louis", getContactName(manager3.getContacts(4)));
		assertEquals("Claudio", getContactName(manager3.getContacts(5)));
		assertEquals("Jurgen", getContactName(manager3.getContacts(6)));
		assertEquals("Roberto", getContactName(manager3.getContacts(7)));
		assertEquals("Slaven", getContactName(manager3.getContacts(8)));
		assertEquals("Ronald", getContactName(manager3.getContacts(9)));
		assertEquals("Alan", getContactName(manager3.getContacts(10)));
		assertEquals("Steve", getContactName(manager3.getContacts(11)));
		assertEquals("Sam", getContactName(manager3.getContacts(12)));
		assertEquals("Mark", getContactName(manager3.getContacts(13)));
	}
	
	@Test 
	public void testFlushStoresAllMeetingsAndOverwrites() {
		manager.flush();
		ContactManager manager2 = new ContactManagerImpl();
		assertEquals(14, manager2.addFutureMeeting(manager2.getContacts(5,6,7), new GregorianCalendar(2017, 11, 2)));
		manager2.flush();
		ContactManager manager3 = new ContactManagerImpl();
		assertEquals(15, manager3.addFutureMeeting(manager3.getContacts(4), new GregorianCalendar(2017, 11, 5)));
	}
	
	@Test 
	public void testFlushStoresMeetingTypesCorrectly() {
		manager.flush();
		ContactManager manager2 = new ContactManagerImpl();
		assertEquals(14, manager2.addFutureMeeting(manager2.getContacts(5,6,7), new GregorianCalendar(2017, 11, 2)));
		manager2.flush();
		ContactManager manager3 = new ContactManagerImpl();
		assertEquals(15, manager3.addFutureMeeting(manager3.getContacts(4), new GregorianCalendar(2017, 11, 5)));
		Contact[] c = manager3.getContacts(4).toArray(new Contact[0]);
		assertEquals(3, manager3.getFutureMeetingList(c[0]).size());
		c = manager3.getContacts(5).toArray(new Contact[0]);
		assertEquals(4, manager3.getPastMeetingListFor(c[0]).size());
	}
	
	private String getContactName(Set<Contact> contacts) {
		Contact[] c = contacts.toArray(new Contact[0]);
		String output = c[0].getName();
		return output;
	}
}