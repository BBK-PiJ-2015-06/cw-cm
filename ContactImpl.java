public class ContactImpl implements Contact {
	
	private int id;
	private String name;
	private String notes;
	
	public ContactImpl(int id, String name, String notes) {
		buildContact(id, name, notes);
	}
	
	public ContactImpl(int id, String name) {
		buildContact(id, name, "");
	}
	
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
	
	@Override
	public int getId() {
		return this.id;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getNotes() {
		return this.notes;
	}
	
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