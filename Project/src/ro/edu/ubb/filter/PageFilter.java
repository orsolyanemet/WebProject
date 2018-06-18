package ro.edu.ubb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter for pages.
 * 
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
public class PageFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		Boolean auth = false;
		if (httpServletRequest.getSession().getAttribute("Authenticated") != null) {
			auth = true;
		}
		if (auth) {
			filterChain.doFilter(request, response);
		} else {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
		}
	}

}
