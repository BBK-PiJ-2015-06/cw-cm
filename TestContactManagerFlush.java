import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.File;

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
	
	@Test 
	public void testFlushCreatesTxtFile() {
		manager.flush();
		assertTrue(new File("." + File.separator + "contacts.txt").exists());
	}
}