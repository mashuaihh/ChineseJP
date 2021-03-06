package web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class MemberFilter
 */
@WebFilter("/MemberFilter")
public class MemberFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MemberFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Boolean login_status = (Boolean) session.getAttribute("login_status");
		String role = (String) session.getAttribute("role");
		
		System.out.println(role);
		if (login_status == null) {
			request.getRequestDispatcher("../ErrNotMember.html").forward(request, response);
		}
		else if (login_status == false || !(role.equals("member") || role.equals("admin"))) {
			request.getRequestDispatcher("../ErrNotMember.html").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
