public class ContactManagerUtils {
	
	public static void assertMeetingType(Meeting meeting, String classname) {
		String meetingClass = meeting.getClass().getName();
		if(!meetingClass.equals(classname)) {
			throw new IllegalArgumentException("ID provided corresponds to invalid meeting type");
		}	
	}
	
}