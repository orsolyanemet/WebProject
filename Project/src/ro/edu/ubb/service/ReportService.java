package ro.edu.ubb.service;

import ro.edu.ubb.common.dao.DAOFactory;
import ro.edu.ubb.common.dao.ReportDAO;
import ro.edu.ubb.dao.DAOException;
import ro.edu.ubb.entity.Report;

/**
 * Service for report.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class ReportService {
	
	private ReportDAO reportDAO;
	private DAOFactory daoFactory;

	public ReportService() {
		daoFactory = DAOFactory.getInstance();
		reportDAO = daoFactory.getReportDAO();
	}

	public Report createReport(Report report) {
		try {
			return reportDAO.createReport(report);
		} catch (DAOException e) {
			throw new ServiceException("Create report failed.");
		}
	}
}
