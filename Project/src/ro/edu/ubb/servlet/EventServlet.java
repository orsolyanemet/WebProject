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

import ro.edu.ubb.entity.Program;
import ro.edu.ubb.entity.ProgramType;
import ro.edu.ubb.entity.RoleType;
import ro.edu.ubb.service.ProgramService;
import ro.edu.ubb.service.ServiceException;
import ro.edu.ubb.service.UserService;

/**
 * Servlet for event.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
@WebServlet("/event.do")
public class EventServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8326508200066736249L;
	private ProgramService programService= new ProgramService();
	private UserService userService= new UserService();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(userService.findByUsername(req.getSession().getAttribute("loggedUsername").toString()).getRoleType()==RoleType.ADMINISTRATOR){
			dispatch("event.jsp", req, res);
		}
		else {
			dispatch("error.jsp",req,res);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Program program=new Program();
			program.setNameProgram(req.getParameter("eventName"));
			program.setDescriptionProgram(req.getParameter("eventDescription"));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String stringDate=req.getParameter("eventDate");
			java.util.Date date = simpleDateFormat.parse(stringDate);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());  
			program.setTargetDate(sqlDate);
			program.setLocation(req.getParameter("eventLocation"));
			program.setProgramType(ProgramType.valueOfIgnoreCase(req.getParameter("eventType")));
			programService.createCheck(program);
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
