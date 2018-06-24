package ro.edu.ubb.entity;

/**
 * Entity for the ProgramTypes.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class ProgramTypes {

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
