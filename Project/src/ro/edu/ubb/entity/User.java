package ro.edu.ubb.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Entity for the users.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idUser;

	private String firstname;

	private String lastname;

	private String email;

	private String username;

	private String password;

	private String phoneNumber;

	private RoleType roleType;

	private List<Program> programs;
	
	public User() {
		super();
	}
	
	public User(Integer idUser, String firstname, String lastname, String email, String username, String password,
			String phoneNumber, RoleType roleType, List<Program> programs) {
		super();
		this.idUser = idUser;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.roleType = roleType;
		this.programs = programs;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", phoneNumber=" + phoneNumber + ", roleType="
				+ roleType + ", programs=" + programs + "]";
	}

}
