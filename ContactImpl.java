/**
 * A ContactImpl is an implementation of the interface Contact, 
 * which is defined in section 3.2 of the document for Assignment 
 * 3 - Contact Manager.
 *
 * @author George Shiangoli
 */

public class ContactImpl implements Contact {
	
	private int id;
	private String name;
	private String notes;
	
	/**
	 * The general constructor accepts three parameters corresponding to
	 * the new contact's ID, name and any notes that may already be known
	 * about the contact.
	 *
	 * The ID provided must be greater than or equal to 0, and the 
	 * String parameters cannot be null.
	 *
	 * @param id the id of the contact to be created
	 * @param name the name of the contact to be created
	 * @param notes the notes of the contact to be created
	 * @throws IllegalArgumentException if the id parameter is less than 1
	 * @throws NullPointerException if the String parameters are null
	 */
	public ContactImpl(int id, String name, String notes) {
		buildContact(id, name, notes);
	}
	
	/**
	 * The restricted constructor accepts two parameters corresponding to
	 * the new contact's ID and name. Note that this differs from the 
	 * general constructor in that no notes are added at the point of
	 * construction. This method instead simply assumes an empty String ""
	 * to the notes field
	 *
	 * The ID provided must be greater than or equal to 0, and the 
	 * name cannot be null.
	 *
	 * @param id the id of the contact to be created
	 * @param name the name of the contact to be created
	 * @throws IllegalArgumentException if the id parameter is less than 1
	 * @throws NullPointerException if the name is null
	 */
	public ContactImpl(int id, String name) {
		buildContact(id, name, "");
	}
	
	/**
	 * The method that gets called from each constructor to ensure that new
	 * objects of type ContactImpl are built correctly and in accordance with
	 * the specification already outlined above @see ContactImpl(int, String, 
	 * String), ContactImpl(int, String)
	 *
	 * @param id the id of the contact to be built
	 * @param name the name of the contact to be built
	 * @param notes the notes of the contact to be built
	 * @throws IllegalArgumentException if the id parameter is less than 1
	 * @throws NullPointerException if the String parameters are null
	 */
	private void buildContact(int id, String name, String notes) {
		if(id < 1) {
			throw new IllegalArgumentException("Contact ID cannot be less than 1");
		}
		if(name == null || notes == null) {
			throw new NullPointerException("Parameters cannot be null");
		}
		this.id = id;
		this.name = name;
		this.notes = notes;
	}
	
	/**
	 *{@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int getId() {
		return this.id;
	}
	
	/**
	 *{@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 *{@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public String getNotes() {
		return this.notes;
	}
	
	/**
	 *{@inheritDoc}
	 *
	 * Reformats the string to ensure that individual notes added 
	 * at different times appear on their own separate lines.
	 *
	 * A null value passed as a parameter is simply ignored and not
	 * added to the existing notes. i.e. the existing notes about the
	 * contact remain unchanged.
	 *
	 * @param note {@inheritDoc}
	 */
	@Override
	public void addNotes(String note) {
		if(note != null) {
			if(!this.notes.equals("")) {
				this.notes+= '\n';
			}
			this.notes += note;
		}
	}
}