package ro.edu.ubb.entity;

import java.io.Serializable;

/**
 * Entity for the ProgramTypes.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class ProgramTypes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idProgramType;
	private String programTypeName;

	public ProgramTypes(){
		super();
	}

	public ProgramTypes(Integer idProgramType, String programTypeName) {
		super();
		this.idProgramType = idProgramType;
		this.programTypeName = programTypeName;
	}

	public Integer getIdProgramType() {
		return idProgramType;
	}

	public void setIdProgramType(Integer idProgramType) {
		this.idProgramType = idProgramType;
	}

	public String getProgramTypeName() {
		return programTypeName;
	}

	public void setProgramTypeName(String programTypeName) {
		this.programTypeName = programTypeName;
	}

	@Override
	public String toString() {
		return "ProgramTypes [idProgramType=" + idProgramType + ", programTypeName=" + programTypeName + "]";
	}
	
}
