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
		Calendar date = new GregorianCalendar(2015, 0, 10); //10th Jan 2015
		PastMeeting myMeeting = new PastMeetingImpl(3, date, contacts, "Notes");
		assertEquals(3, myMeeting.getId());
	}
	
	@Test 
	public void testGetDateMethod() {
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact1);
		Calendar date = new GregorianCalendar(2015, 0, 12); //12th Jan 2015
		PastMeeting myMeeting = new PastMeetingImpl(2, date, contacts, "Notes");
		assertEquals(date, myMeeting.getDate());
	}
	
	@Test 
	public void testGetContactsMethodAfterAddingThreeDifferentContacts() {
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		contacts.add(contact3);
		Calendar date = new GregorianCalendar(2015, 0, 10); //10th Jan 2015
		PastMeeting myMeeting = new PastMeetingImpl(3, date, contacts, "Notes");
		assertEquals(3, myMeeting.getContacts().size());
		assertTrue(myMeeting.getContacts().containsAll(contacts));
	}
	
	@Test 
	public void testAddingSameContactMultipleTimes() {
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact1);
		contacts.add(contact2);
		contacts.add(contact1);
		Calendar date = new GregorianCalendar(2015, 0, 10); //10th Jan 2015
		PastMeeting myMeeting = new PastMeetingImpl(3, date, contacts, "Notes");
		assertEquals(2, myMeeting.getContacts().size());
		assertTrue(myMeeting.getContacts().containsAll(contacts));
	}
	
	@Test 
	public void testGetNotesMethod() {
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact1);
		Calendar date = new GregorianCalendar(2015, 1, 12); //12th Feb 2015
		PastMeeting myMeeting = new PastMeetingImpl(5, date, contacts, "Notes");
		assertEquals("Notes", myMeeting.getNotes());
	}
	
	@Test(timeout = 1000)
	public void testAddingMillionContactsToMeetingFinishesBeforeOneSecond() {
		Set<Contact> contacts = new HashSet<Contact>();
		for(int i = 1; i <= 1000000; i++) {
			contacts.add(new MockContact());
		}
		Calendar date = new GregorianCalendar(2015, 1, 12); //12th Feb 2015
		PastMeeting myMeeting = new PastMeetingImpl(6, date, contacts, "Notes");
		assertEquals(1000000, myMeeting.getContacts().size());
		assertTrue(myMeeting.getContacts().containsAll(contacts));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingEmptySetOfContacts() {
		Set<Contact> contacts = new HashSet<Contact>();
		Calendar date = new GregorianCalendar(2015, 6, 11); //11th Jul 2015
		PastMeeting myMeeting = new PastMeetingImpl(9, date, contacts, "Notes");
	}
}