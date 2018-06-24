package ro.edu.ubb.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.edu.ubb.entity.Message;
import ro.edu.ubb.entity.RoleType;
import ro.edu.ubb.service.MessageService;
import ro.edu.ubb.service.ServiceException;
import ro.edu.ubb.service.UserService;

/**
* Servlet for compose message.
* 
* @author Nemet Orsolya, noim1553, 532/1 csoport
*
*/
@WebServlet("/composemessage.do")
public class ComposeMessageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8326508200066736249L;
	private MessageService messageService= new MessageService();
	private UserService userService= new UserService();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(userService.findByUsername(req.getSession().getAttribute("loggedUsername").toString()).getRoleType()==RoleType.ADMINISTRATOR){
			dispatch("composemessage.jsp", req, res);
		}
		else {
			dispatch("error.jsp",req,res);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Message message=new Message();
			message.setSubject(req.getParameter("subject"));
			message.setSendTo(req.getParameter("sendTo"));
			message.setMess(req.getParameter("message"));
			long date= System.currentTimeMillis();
			Date datesql = new Date(date);
			message.setDate(datesql);
			messageService.createMessage(message);
			
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
