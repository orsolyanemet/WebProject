package ro.edu.ubb.entity;

import java.sql.Date;

/**
 * Entity for the Tasks.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class Task{
	
	private Integer idTask;

	private String taskName;

	private String details;

	private Boolean isSolved;

	private Date deadline;

	private String assignedTo;

	private String partOf;
	
	public Task() {
		super();
	}

	public Task(Integer idTask, String taskName, String details, Boolean isSolved, Date deadline, String assignedTo,
			String partOf) {
		super();
		this.idTask = idTask;
		this.taskName = taskName;
		this.details = details;
		this.isSolved = isSolved;
		this.deadline = deadline;
		this.assignedTo = assignedTo;
		this.partOf = partOf;
	}

	public Integer getIdTask() {
		return idTask;
	}

	public void setIdTask(Integer idTask) {
		this.idTask = idTask;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Boolean getIsSolved() {
		return isSolved;
	}

	public void setIsSolved(Boolean isSolved) {
		this.isSolved = isSolved;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getPartOf() {
		return partOf;
	}

	public void setPartOf(String partOf) {
		this.partOf = partOf;
	}

	@Override
	public String toString() {
		return "Task [idTask=" + idTask + ", taskName=" + taskName + ", details=" + details + ", isSolved=" + isSolved
				+ ", deadline=" + deadline + ", assignedTo=" + assignedTo + ", partOf=" + partOf + "]";
	}

}
