package ro.edu.ubb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.edu.ubb.entity.RoleType;
import ro.edu.ubb.service.MessageService;
import ro.edu.ubb.service.UserService;

/**
 * Servlet for inbox.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
@WebServlet("/inbox.do")
public class InboxServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageService();
	private UserService userService=new UserService();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(userService.findByUsername(req.getSession().getAttribute("loggedUsername").toString()).getRoleType()==RoleType.ORGANIZER){
			dispatch("inbox.jsp", req, res);
		}
		else {
			dispatch("error.jsp",req,res);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getSession().setAttribute("userMessages", messageService.getAllMessages((String)req.getSession().getAttribute("loggedUsername")));
	}

	public void dispatch(String jsp, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		if (jsp != null) {
			RequestDispatcher rd = req.getRequestDispatcher(jsp);
			rd.forward(req, res);
		}
	}
}
