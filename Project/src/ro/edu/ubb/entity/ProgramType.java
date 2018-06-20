package ro.edu.ubb.entity;

/**
 * Enum for the types of programs.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public enum ProgramType {
	TEAM_BUILDING("Team building"), MUSIC_FESTIVAL("Music festival"), WEDDING("Wedding"), WEDDING_ANNIVERSARY("Wedding anniversary"),BIRTHDAY(
			"Birthday"), BUSINESS_DINNER("Business dinner"), SEMINAR("Seminar"), CONFERENCE("Conference");
	private final String progType;

	private ProgramType(String programType) {
		this.progType = programType;
	}

	public String getProgramType() {
		return progType;
	}

}
