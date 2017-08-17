package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.training.helper.command.Command;
import by.epam.training.helper.constant.ParameterName;
/**
 * @author Nikolaev Ilya
 *	To change the language on the site
 */
public class SetLocale implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String locale = request.getParameter(ParameterName.LOCALE);
		 
		 HttpSession session = request.getSession(true);
		 session.setAttribute(ParameterName.LOCALE, locale);
		 response.sendRedirect("controller?command=home");
	}
	
}
