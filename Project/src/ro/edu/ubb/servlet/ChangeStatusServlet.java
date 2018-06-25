package ro.edu.ubb.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.edu.ubb.entity.Report;
import ro.edu.ubb.entity.RoleType;
import ro.edu.ubb.entity.Task;
import ro.edu.ubb.service.ReportService;
import ro.edu.ubb.service.ServiceException;
import ro.edu.ubb.service.TaskService;
import ro.edu.ubb.service.UserService;

/**
 * Servlet for changing task status.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
@WebServlet("/changestatus.do")
public class ChangeStatusServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8326508200066736249L;
	private TaskService taskService= new TaskService();
	private ReportService reportService= new ReportService();
	private UserService userService= new UserService();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(userService.findByUsername(req.getSession().getAttribute("loggedUsername").toString()).getRoleType()==RoleType.ORGANIZER){
			dispatch("changestatus.jsp", req, res);
		}
		else {
			dispatch("error.jsp",req,res);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			if(req.getParameter("report")!="" || req.getParameter("report")!=null ) {
				Report report=new Report();
				report.setPartOf(req.getParameter("partOf"));
				report.setReport(req.getParameter("report"));
				report.setWrittenBy((String)req.getSession().getAttribute("loggedUsername"));
				Date currentDate=new Date(Calendar.getInstance().getTime().getTime());
				System.out.println(currentDate);
				report.setReportDate(currentDate);
				reportService.createReport(report);
			}
			if(req.getParameter("solved").equals("true")) {
				Task task=new Task();
				task.setTaskName(req.getParameter("partOf"));
				task.setIsSolved(true);
				task.setAssignedTo((String)req.getSession().getAttribute("loggedUsername"));
				taskService.updateTask(task);
			}
		}
		catch(ServiceException e) {
			dispatch("error.jsp",req,res);
		}
	}

	public void dispatch(String jsp, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		if (jsp != null) {
			RequestDispatcher rd = req.getRequestDispatcher(jsp);
			rd.forward(req, res);
		}
	}
}
