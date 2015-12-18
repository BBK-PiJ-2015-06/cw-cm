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
}