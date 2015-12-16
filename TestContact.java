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
	
}