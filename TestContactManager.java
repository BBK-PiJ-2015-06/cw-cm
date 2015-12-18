import org.junit.*;
import static org.junit.Assert.*;

public class TestContactManager {
	
	private ContactManager manager;
	
	@Before
	public void init() {
		manager = new ContactManagerImpl();
	}
	
	@Test 
	public void testAddingFirstContactWithCorrectParams() {
		assertEquals(1, manager.addNewContact("Name", "Notes"));
	}
	
	@Test 
	public void testAddingMultipleNewContacts() {
		assertEquals(1, manager.addNewContact("Name", "Notes"));
		assertEquals(2, manager.addNewContact("Name2", "Notes2"));
		assertEquals(3, manager.addNewContact("Name3", "Notes3"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddingContactWithEmptyStringName() {
		manager.addNewContact("", "Notes");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddingContactWithEmptyStringNotes() {
		manager.addNewContact("Name", "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddingContactWithAllEmptyStrings() {
		manager.addNewContact("", "");
	}
}