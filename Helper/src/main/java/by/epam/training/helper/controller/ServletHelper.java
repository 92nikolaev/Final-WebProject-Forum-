package by.epam.training.helper.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.CommandHelper;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.command.exception.CommandNotFoundException;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;


/**
 * Servlet implementation class ServletHelper
 */
@WebServlet(asyncSupported = true, name = "ServletHelper", urlPatterns = { "/controller" })
public class ServletHelper extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(ServletHelper.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		CommandHelper commandHelper = CommandHelper.getInstance();
		String commandName = request.getParameter(ParameterName.COMMAND);
		Command command;
				System.out.println(commandName);
		try {
			command = commandHelper.getCommand(commandName);
			command.execute(request, response);
		} catch (CommandNotFoundException e) {
			logger.error(e);
			response.sendRedirect(Url.ERROR);
		} catch (CommandException e) {
			logger.error(e);
			response.sendRedirect(Url.ERROR);
		}

	}

}
