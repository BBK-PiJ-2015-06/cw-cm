import org.junit.*;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestPastMeeting {
	
	private Contact contact1;
	private Contact contact2;
	private Contact contact3;
	
	@Before
	public void init() {
		contact1 = new MockContact();
		contact2 = new MockContact();
		contact3 = new MockContact();
	}
	
	@Test 
	public void testGetIdMethod() {
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		contacts.add(contact3);
		Calendar date = new GregorianCalendar(2015, 0, 10); //10th Jan 2016
		Meeting myMeeting = new PastMeetingImpl(3, date, contacts, "Notes");
		assertEquals(3, myMeeting.getId());
	}
	
	@Test 
	public void testGetDateMethod() {
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact1);
		Calendar date = new GregorianCalendar(2015, 0, 12); //12th Jan 2016
		Meeting myMeeting = new PastMeetingImpl(2, date, contacts, "Notes");
		assertEquals(date, myMeeting.getDate());
	}
	
	@Test 
	public void testGetContactsMethodAfterAddingThreeDifferentContacts() {
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		contacts.add(contact3);
		Calendar date = new GregorianCalendar(2015, 0, 10); //10th Jan 2016
		Meeting myMeeting = new PastMeetingImpl(3, date, contacts, "Notes");
		assertEquals(3, myMeeting.getContacts().size());
		assertTrue(myMeeting.getContacts().containsAll(contacts));
	}
	
	@Test 
	public void testAddingSameContactMultipleTimes() {
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		contacts.add(contact1);
		Calendar date = new GregorianCalendar(2015, 0, 10); //10th Jan 2016
		Meeting myMeeting = new PastMeetingImpl(3, date, contacts, "Notes");
		assertEquals(2, myMeeting.getContacts().size());
		assertTrue(myMeeting.getContacts().containsAll(contacts));
	}
}