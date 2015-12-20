public class ContactManagerUtils {
	
	public static Meeting getMeetingOfType(Meeting meeting, String classname) {
		if(meeting != null) {
			String meetingClass = meeting.getClass().getName();
			if(!meetingClass.equals(classname)) {
				throw new IllegalArgumentException("ID provided corresponds to invalid meeting type");
			}
			return meeting;
		} else {
			return null;
		}
	}

}