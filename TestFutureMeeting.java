import org.junit.*;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * This testing class is used to test the implementation of the interface
 * FutureMeeting.
 *
 * @author George Shiangoli
 */
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
	
	@Test 
	public void testGetDateMethod() {
		contacts.add(new MockContact());
		Meeting myMeeting = new FutureMeetingImpl(1, date, contacts);
		assertEquals(date, myMeeting.getDate());
	}
	
	@Test 
	public void testGetContactsMethodAfterAddingThreeDifferentContacts() {
		contacts.add(new MockContact());
		contacts.add(new MockContact());
		contacts.add(new MockContact());
		Meeting myMeeting = new FutureMeetingImpl(1, date, contacts);
		assertEquals(3, myMeeting.getContacts().size());
		assertTrue(myMeeting.getContacts().containsAll(contacts));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingEmptySetOfContacts() {
		Meeting myMeeting = new FutureMeetingImpl(1, date, contacts);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingZeroAsId() {
		contacts.add(new MockContact());
		Meeting myMeeting = new FutureMeetingImpl(0, date, contacts);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingNegativeNumberAsId() {
		contacts.add(new MockContact());
		for(int i = -1; i > -10; i--) {
			Meeting myMeeting = new FutureMeetingImpl(i, date, contacts);
		}
	}
	
	@Test(expected = NullPointerException.class) 
	public void testPassingNullDate() {
		contacts.add(new MockContact());
		Meeting myMeeting = new FutureMeetingImpl(1, null, contacts);
	}
	
	@Test(expected = NullPointerException.class) 
	public void testPassingNullContacts() {
		Meeting myMeeting = new FutureMeetingImpl(1, date, null);
	}
	
	@Test(expected = NullPointerException.class) 
	public void testPassingNullAsAllParams() {
		Meeting myMeeting = new FutureMeetingImpl(1, null, null);
	}
	
	@Ignore
	@Test(expected = IllegalArgumentException.class) 
	public void testIdValidityCheckedFirst() {
		Meeting myMeeting = new FutureMeetingImpl(0, null, null);
	}
	
	@Test(expected = NullPointerException.class) 
	public void testDateValidityCheckedSecond() {
		Meeting myMeeting = new FutureMeetingImpl(1, null, null);
	}
	
	@Test(expected = NullPointerException.class) 
	public void testContactNullValidityCheckedLast() {
		Meeting myMeeting = new FutureMeetingImpl(1, date, null);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testContactEmptyValidityCheckedFourth() {
		Meeting myMeeting = new FutureMeetingImpl(1, date, contacts);
	}
}