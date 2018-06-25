package ro.edu.ubb.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Entity for the Reports.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class Report implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8016085986005506682L;

	private Integer idReport;

	private String report;

	private Date reportDate;

	private String partOf;
	
	private String writtenBy;
	
	public Report() {
		super();
	}

	public Report(Integer idReport, String report, Date reportDate, String partOf, String writtenBy) {
		super();
		this.idReport = idReport;
		this.report = report;
		this.reportDate = reportDate;
		this.partOf = partOf;
		this.writtenBy = writtenBy;
	}

	public Integer getIdReport() {
		return idReport;
	}

	public void setIdReport(Integer idReport) {
		this.idReport = idReport;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getPartOf() {
		return partOf;
	}

	public void setPartOf(String partOf) {
		this.partOf = partOf;
	}

	public String getWrittenBy() {
		return writtenBy;
	}

	public void setWrittenBy(String writtenBy) {
		this.writtenBy = writtenBy;
	}

	@Override
	public String toString() {
		return "Report [idReport=" + idReport + ", report=" + report + ", reportDate=" + reportDate + ", partOf="
				+ partOf + ", writtenBy=" + writtenBy + "]";
	}

}
