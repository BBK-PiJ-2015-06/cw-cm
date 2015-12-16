public class ContactImpl implements Contact {
	
	private int id;
	private String name;
	private String notes;
	
	public ContactImpl(int id, String name, String notes) {
		this.id = id;
		this.name = name;
		this.notes = notes;
	}
	
	public ContactImpl(int id, String name) {
		this.id = id;
		this.name = name;
		this.notes = "";
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
		this.notes += '\n' + note;
	}
}