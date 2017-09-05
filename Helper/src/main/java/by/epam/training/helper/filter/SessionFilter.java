package by.epam.training.helper.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.training.helper.constant.Url;
/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter(urlPatterns = {"/*"})
public class SessionFilter implements Filter {

	 @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	        
	    }

	    @Override
	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	    	HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			HttpSession session = request.getSession(false);
			if(session == null){
				request.getSession(true);
				response.sendRedirect(Url.REDIRECT_HOME_PAGE);
			}else{
				filterChain.doFilter(servletRequest, servletResponse);
			}
	    }
	    @Override
	    public void destroy() {
	    	
	    }
}
