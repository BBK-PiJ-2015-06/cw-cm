import org.junit.*;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestFutureMeeting {
	
	private Set<Contact> contacts;
	private Calendar date;
	
	@Before
	public void init() {
		contacts = new HashSet<Contact>();
		date = new GregorianCalendar(2016, 5, 22); //date set for 22nd June 2016
	}
	
	@Test 
	public void testGetIdMethod() {
		contacts.add(new MockContact());
		Meeting myMeeting = new FutureMeetingImpl(1, date, contacts);
		assertEquals(1, myMeeting.getId());
	}
}