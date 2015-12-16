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
	public void testsGetterMethodsWithRestrictedConstructor() {
		Contact myContact = new ContactImpl(2, "Kyle Walker");
		assertEquals(2, myContact.getId());
		assertEquals("Kyle Walker", myContact.getName());
		assertEquals("", myContact.getNotes());
	}
}