import org.junit.*;
import static org.junit.Assert.*;
import java.util.Set;

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
	
	@Test(expected = NullPointerException.class)
	public void testAddingContactWithNullName() {
		manager.addNewContact(null, "Notes");
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddingContactWithNullNotes() {
		manager.addNewContact("Name", null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddingContactWithNullParams() {
		manager.addNewContact(null, null);
	}
	
	@Test 
	public void testPassingEmptyStringToGetContactsOnEmptyContactSet() {
		Set<Contact> output = manager.getContacts("");
		assertEquals(0, output.size());
	}
	
}