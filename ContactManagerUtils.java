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

}