import org.junit.*;
import static org.junit.Assert.*;
import java.util.Set;
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
}