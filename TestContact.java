import org.junit.*;
import static org.junit.Assert.*;

/**
 * This testing class is used to test the implementation of the interface
 * Contact.
 *
 * @author George Shiangoli
 */
public class TestContact {
	
	@Test 
	public void testGetterMethodsWithGeneralConstructor() {
		Contact myContact = new ContactImpl(3, "Harry Kane", "Awesome striker");
		assertEquals(3, myContact.getId());
		assertEquals("Harry Kane", myContact.getName());
		assertEquals("Awesome striker", myContact.getNotes());
	}
	
	@Test 
	public void testGetterMethodsWithRestrictedConstructor() {
		Contact myContact = new ContactImpl(2, "Kyle Walker");
		assertEquals(2, myContact.getId());
		assertEquals("Kyle Walker", myContact.getName());
		assertEquals("", myContact.getNotes());
	}
	
	@Test 
	public void testAddingNotesToExistingNotes() {
		Contact myContact = new ContactImpl(1, "Hugo Lloris", "Great keeper");
		myContact.addNotes("Made mistakes last game");
		assertEquals("Great keeper\nMade mistakes last game", myContact.getNotes());
	}
	
	@Test 
	public void testAddingNotesToEmptyString() {
		Contact myContact = new ContactImpl(4, "Toby Alderwereild");
		myContact.addNotes("Made mistakes last game");
		assertEquals("Made mistakes last game", myContact.getNotes());
	}
	
	@Test 
	public void testAddingNullNotesToExistingNotes() {
		Contact myContact = new ContactImpl(1, "Hugo Lloris", "Great keeper");
		myContact.addNotes(null);
		assertEquals("Great keeper", myContact.getNotes());
	}
	
	@Test 
	public void testAddingNullNotesToEmptyString() {
		Contact myContact = new ContactImpl(1, "Hugo Lloris");
		myContact.addNotes(null);
		assertEquals("", myContact.getNotes());
	}
	
	@Test 
	public void testAddingCombinationOfNullAndCorrectNotesToExistingNotes() {
		Contact myContact = new ContactImpl(1, "Hugo Lloris", "Great keeper");
		myContact.addNotes(null);
		myContact.addNotes(null);
		myContact.addNotes("but made mistakes last week");
		myContact.addNotes(null);
		assertEquals("Great keeper\nbut made mistakes last week", myContact.getNotes());
	}
	
	@Test 
	public void testAddingCombinationOfNullAndCorrectNotesToEmptyString() {
		Contact myContact = new ContactImpl(1, "Hugo Lloris");
		myContact.addNotes(null);
		myContact.addNotes("Great keeper");
		myContact.addNotes(null);
		myContact.addNotes(null);
		myContact.addNotes("but made mistakes last week");
		assertEquals("Great keeper\nbut made mistakes last week", myContact.getNotes());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingIdLessThanZeroWithGeneralConstructor() {
		for(int i = -1; i > -10; i--) {
			Contact myContact = new ContactImpl(i, "NewContact", "Notes");
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingZeroIdWithGeneralConstructor() {
		Contact myContact = new ContactImpl(0, "NewContact", "Notes");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingIdLessThanZeroWithRestrictedConstructor() {
		for(int i = -1; i > -10; i--) {
			Contact myContact = new ContactImpl(i, "NewContact");
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingZeroIdWithRestrictedConstructor() {
		Contact myContact = new ContactImpl(0, "NewContact");
	}
	
	@Test(expected = NullPointerException.class)
	public void testPassingNullNameToGeneralConstructor() {
		Contact myContact = new ContactImpl(1, null, "Notes");
	}
	
	@Test(expected = NullPointerException.class)
	public void testPassingNullNotesToGeneralConstructor() {
		Contact myContact = new ContactImpl(1, "NewContact", null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPassingAllNullParametersToGeneralConstructor() {
		Contact myContact = new ContactImpl(1, null, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPassingNullNameToRestrictedConstructor() {
		Contact myContact = new ContactImpl(1, null);
	}
}