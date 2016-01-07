import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * The class includes a selection of static methods that are commonly used in 
 * the Contact Manager application, which provide a means for refactoring and
 * minimising the duplication of source code.
 *
 * @author George Shiangoli
 */
public class ContactManagerUtils {
	
	/**
	 * Provides a means of sorting a list of Meetings chronologically. Returns 
	 * a negative number if the first meeting takes place before the second 
	 * meeting, a positive number visa versa, and zero if both meetings occur
	 * at exactly the same moment in time
	 *
	 * @param m1 a meeting in the list
	 * @param m2 the second meeting in the list that is to be compared to m1
	 * @return an integer that is positive, negative or zero depending on the 
	 *         chronological order of meetings m1 and m2
	 */
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
	
	/**
	 * Removes duplicate meetings from a list of meetings. 
	 *
	 * Two meetings A and B are regarded as being duplicate if they both contain
	 * exactly the same contacts and the meetings occur at the same moment in time.
	 *
	 * @param originalList the original list of meetings that may contain duplicates
	 */
	public static void removeDuplicates(List<? extends Meeting> originalList) {
		for(int i = 0; i < originalList.size(); i++) {
			ListIterator<? extends Meeting> iterator = originalList.listIterator(i + 1);
			while(iterator.hasNext()) {
				Meeting m = iterator.next();
				Calendar d1 = originalList.get(i).getDate();
				Set<Contact> c1 = originalList.get(i).getContacts();
				Calendar d2 = m.getDate();
				Set<Contact> c2 = m.getContacts();
				if(d1.equals(d2) && c1.size() == c2.size() && c1.containsAll(c2)) {
						iterator.remove();
				}
			}
		}
	}
	
	/**
	 * Checks whether an array of objects contain any null values. If a null value
	 * is found amongst the objects, a NullPointerException is thrown.
	 *
	 * @param args an arbitary number of objects
	 * @throws NullPointerException if any of the objects contain null values
	 */
	public static void nullParamChecker(Object... args) {
		for(Object o : args) {
			if(o == null) {
				throw new NullPointerException("Cannot have null parameters");
			}
		}
	}
	
	/**
	 * Checks the validity of a contact compared to a set of known contacts. A contact
	 * is regarded as invalid if it is null or if it is not recognised as a member of the
	 * known set of contacts i.e. if the contact is not a subset of the set of known contacts. 
	 *
	 * @param contact the contact to be checked for validity
	 * @param knownContacts the set of contacts that the first parameter is to be checked
	 *                      against
	 * @throws NullPointerException if the contact provided is null
	 * @throws IllegalArgumentException if the contact is not recognised as being a member
	 *                                  of the known contact set
	 */
	public static void contactChecker(Contact contact, Set<Contact> knownContacts) {
		ContactManagerUtils.nullParamChecker(contact);
		if(!knownContacts.contains(contact)) {
			throw new IllegalArgumentException("Contact not known");
		}
	}

}