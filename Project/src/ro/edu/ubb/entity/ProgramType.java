package ro.edu.ubb.entity;

/**
 * Enum for the types of programs.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public enum ProgramType {
	TEAM_BUILDING("TEAM_BUILDING"), MUSIC_FESTIVAL("MUSIC_FESTIVAL"), WEDDING("WEDDING"), WEDDING_ANNIVERSARY("WEDDING_ANNIVERSARY"),BIRTHDAY(
			"BIRTHDAY"), BUSINESS_DINNER("BUSINESS_DINNER"), SEMINAR("SEMINAR"), CONFERENCE("CONFERENCE");
	private final String progType;

	private ProgramType(String programType) {
		this.progType = programType;
	}

	public String getProgramType() {
		return progType;
	}

}
