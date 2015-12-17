/**
 * A MockContact implements the interface Contact and returns
 * the basic values associated with a Contact. This class has been
 * created to enable the implementation of other interfaces to be 
 * tested independantly from the class ContactImpl.
 *
 * @author George Shiangoli
 */

public class MockContact implements Contact {
	
	@Override
	public int getId() {
		return 1;
	}
	
	@Override
	public String getName() {
		return "Mock";
	}
	
	@Override 
	public String getNotes() {
		return "Notes";
	}
	
	@Override
	public void addNotes(String note) {}
	
}