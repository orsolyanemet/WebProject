package ro.edu.ubb.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.edu.ubb.entity.RoleType;
import ro.edu.ubb.entity.Task;
import ro.edu.ubb.service.ServiceException;
import ro.edu.ubb.service.TaskService;
import ro.edu.ubb.service.UserService;

/**
 * Servlet for adding task.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
@WebServlet("/addtask.do")
public class AddTaskServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8326508200066736249L;
	private TaskService taskService= new TaskService();
	private UserService userService= new UserService();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(userService.findByUsername(req.getSession().getAttribute("loggedUsername").toString()).getRoleType()==RoleType.ADMINISTRATOR){
			dispatch("addtask.jsp", req, res);
		}
		else {
			dispatch("error.jsp",req,res);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Task task=new Task();
			task.setTaskName(req.getParameter("taskName"));
			task.setDetails(req.getParameter("details"));
			task.setIsSolved(false);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String stringDate=req.getParameter("deadline");
			java.util.Date date = simpleDateFormat.parse(stringDate);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());  
			task.setDeadline(sqlDate);
			task.setAssignedTo(req.getParameter("assignedTo"));
			task.setPartOf(req.getParameter("partOf"));
			taskService.createTask(task);
		}
		catch(ServiceException | ParseException e) {
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
