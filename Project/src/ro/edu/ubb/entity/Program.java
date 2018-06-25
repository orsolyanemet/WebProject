package ro.edu.ubb.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


/**
 * Entity for the Programs.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class Program implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idProgram;

	private String nameProgram;

	private String descriptionProgram;
	
	private Date targetDate;

	private String location;

	private ProgramType programType;

	private List<User> users;
	
	public Program(){
			super();
	}

	public Program(Integer idProgram, String nameProgram, String descriptionProgram, Date targetDate, String location,
			ProgramType programType, List<User> users) {
		super();
		this.idProgram = idProgram;
		this.nameProgram = nameProgram;
		this.descriptionProgram = descriptionProgram;
		this.targetDate = targetDate;
		this.location = location;
		this.programType = programType;
		this.users = users;
	}

	public Integer getIdProgram() {
		return idProgram;
	}

	public void setIdProgram(Integer idProgram) {
		this.idProgram = idProgram;
	}

	public String getNameProgram() {
		return nameProgram;
	}

	public void setNameProgram(String nameProgram) {
		this.nameProgram = nameProgram;
	}

	public String getDescriptionProgram() {
		return descriptionProgram;
	}

	public void setDescriptionProgram(String descriptionProgram) {
		this.descriptionProgram = descriptionProgram;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ProgramType getProgramType() {
		return programType;
	}

	public void setProgramType(ProgramType programType) {
		this.programType = programType;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Program [idProgram=" + idProgram + ", nameProgram=" + nameProgram + ", descriptionProgram="
				+ descriptionProgram + ", targetDate=" + targetDate + ", location=" + location + ", programType="
				+ programType + ", users=" + users + "]";
	}

}
