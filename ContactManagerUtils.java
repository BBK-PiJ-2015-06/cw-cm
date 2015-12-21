import java.util.Set;

public class ContactManagerUtils {
	
	public static int chronologicalChecker(Meeting m1, Meeting m2) {
		int output = 0;
		if(m1.getDate().before(m2.getDate())) {
			output = -1;
		}
		if(m1.getDate().after(m2.getDate())) {
			output = 1;
		}
		return output;
	}
	
	public static void nullParamChecker(Object... args) {
		for(Object o : args) {
			if(o == null) {
				throw new NullPointerException("Cannot have null parameters");
			}
		}
	}
	
	public static void contactChecker(Contact contact, Set<Contact> knownContacts) {
		ContactManagerUtils.nullParamChecker(contact);
		if(!knownContacts.contains(contact)) {
			throw new IllegalArgumentException("Contact not known");
		}
	}

}