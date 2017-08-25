package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.training.helper.command.Command;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
/**
 * To change the language on the site
 * @author Nikolaev Ilya
 */
public class SetLocale implements Command {
	/**
	 *	@param request - request from client to get parameters to work with it
	 *  @param response - send response to client with parameters to work with on client side
	 *  @throws IOException  
     * 	@throws ServletException
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String locale = request.getParameter(ParameterName.LOCALE);
		 HttpSession session = request.getSession(true);
		 session.setAttribute(ParameterName.LOCALE, locale);
		 response.sendRedirect(Url.REDIRECT_HOME_PAGE);
	}
	
}
