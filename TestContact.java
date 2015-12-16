import org.junit.*;
import static org.junit.Assert.*;

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
}