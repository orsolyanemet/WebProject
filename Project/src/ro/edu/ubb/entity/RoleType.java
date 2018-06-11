package ro.edu.ubb.entity;

/**
 * Enum for the types of roles.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public enum RoleType {
	ADMINISTRATOR("ADMINISTRATOR"), ORGANIZER("ORGANIZER");
	private String role;

	RoleType(String role) {
		this.role = role;
	}

	public String getRoleType() {
		return role;
	}
}
