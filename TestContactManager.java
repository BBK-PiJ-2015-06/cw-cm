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
	
	@Test 
	public void testPassingEmptyStringToGetContacts() {
		manager.addNewContact("Name", "Notes");
		assertEquals(1, manager.getContacts("").size());
		manager.addNewContact("Name", "Notes");
		assertEquals(2, manager.getContacts("").size());
		manager.addNewContact("Name", "Notes");
		assertEquals(3, manager.getContacts("").size());
	}
	
	@Test(expected = NullPointerException.class) 
	public void testPassingNullValueToGetContacts() {
		String name = null;
		manager.getContacts(name);
	} 
	
	@Test 
	public void testPassingNonEmptyStringToGetContactsOnEmptyContactSet() {
		assertEquals(0, manager.getContacts("Hugo").size());
	}
	
	@Test 
	public void testPassingNonEmptyStringToGetContacts() {
		manager.addNewContact("Mauricio", "Notes");
		manager.addNewContact("Arsene", "Notes");
		manager.addNewContact("Jose", "Notes");
		assertEquals(3, manager.getContacts("").size());
		assertEquals(1, manager.getContacts("Mauricio").size());
		assertEquals(2, manager.getContacts("se").size());
		assertEquals(0, manager.getContacts("Brendan").size());
	}
	
	@Test 
	public void testPassingNonEmptyStringToGetContacts2() {
		manager.addNewContact("Mauricio", "Notes");
		manager.addNewContact("Arsene", "Notes");
		manager.addNewContact("Jose", "Notes");
		assertEquals(0, manager.getContacts("ee").size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingNoIdsToGetContacts() {
		manager.getContacts();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingZeroIdToGetContacts() {
		manager.getContacts(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingZeroIdToGetContactsOnNonEmptySet() {
		manager.addNewContact("Mauricio", "Notes");
		manager.getContacts(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingNegativeIdToGetContacts() {
		manager.getContacts(1, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingNegativeIdToGetContactsOnNonEmptySet() {
		manager.addNewContact("Mauricio", "Notes");
		manager.getContacts(1, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingInvalidIdToGetContactsOnEmptySet() {
		manager.getContacts(1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingInvalidIdToGetContacts() {
		manager.addNewContact("Mauricio", "Notes");
		manager.getContacts(2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPassingInvalidIdToGetContactsAmongstValidIds() {
		manager.addNewContact("Mauricio", "Notes");
		manager.addNewContact("Arsene", "Notes");
		manager.getContacts(1,2,3);
	}
	
	@Test 
	public void testPassingSingleIdToGetContacts() {
		manager.addNewContact("Mauricio", "Notes");
		manager.addNewContact("Arsene", "Notes");
		manager.addNewContact("Jose", "Notes");
		manager.addNewContact("Louis", "Notes");
		Set<Contact> output = manager.getContacts(1,2);
		assertEquals(2, output.size());
		for(Contact c : output) {
			System.out.println(c.getName());
		}
	}
	
	@Test 
	public void testPassingSingleIdToGetContacts2() {
		manager.addNewContact("Mauricio", "Notes");
		manager.addNewContact("Arsene", "Notes");
		manager.addNewContact("Jose", "Notes");
		manager.addNewContact("Louis", "Notes");
		Set<Contact> output = manager.getContacts(1,2,3,4);
		assertEquals(4, output.size());
		for(Contact c : output) {
			System.out.println(c.getName());
		}
	}
	
	@Test 
	public void testPassingSingleIdToGetContacts3() {
		manager.addNewContact("Mauricio", "Notes");
		manager.addNewContact("Arsene", "Notes");
		manager.addNewContact("Jose", "Notes");
		manager.addNewContact("Louis", "Notes");
		Set<Contact> output = manager.getContacts(2,4);
		assertEquals(2, output.size());
		for(Contact c : output) {
			System.out.println(c.getName());
		}
	}
	
	@Test 
	public void testPassingSingleIdToGetContacts4() {
		manager.addNewContact("Mauricio", "Notes");
		manager.addNewContact("Arsene", "Notes");
		manager.addNewContact("Jose", "Notes");
		manager.addNewContact("Louis", "Notes");
		Set<Contact> output = manager.getContacts(1,3);
		assertEquals(2, output.size());
		for(Contact c : output) {
			System.out.println(c.getName());
		}
	}
	
	@Test 
	public void testPassingSingleIdToGetContacts5() {
		manager.addNewContact("Mauricio", "Notes");
		manager.addNewContact("Arsene", "Notes");
		manager.addNewContact("Jose", "Notes");
		manager.addNewContact("Louis", "Notes");
		Set<Contact> output = manager.getContacts(4);
		assertEquals(1, output.size());
		for(Contact c : output) {
			System.out.println(c.getName());
		}
	}
}