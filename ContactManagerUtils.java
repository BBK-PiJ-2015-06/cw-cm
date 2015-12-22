import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
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
	
	public static void removeDuplicates(List<Meeting> originalList) {
		for(int i = 0; i < originalList.size(); i++) {
			ListIterator<Meeting> iterator = originalList.listIterator(i + 1);
			while(iterator.hasNext()) {
				Meeting m = iterator.next();
				Calendar d1 = originalList.get(i).getDate();
				Set<Contact> c1 = originalList.get(i).getContacts();
				Calendar d2 = m.getDate();
				Set<Contact> c2 = m.getContacts();
				if(d1.get(Calendar.YEAR) == d2.get(Calendar.YEAR) 
					&& d1.get(Calendar.MONTH) == d2.get(Calendar.MONTH) 
					&& d1.get(Calendar.DAY_OF_MONTH) == d2.get(Calendar.DAY_OF_MONTH)
					&& c1.size() == c2.size() && c1.containsAll(c2)) {
						iterator.remove();
				}
			}
		}
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