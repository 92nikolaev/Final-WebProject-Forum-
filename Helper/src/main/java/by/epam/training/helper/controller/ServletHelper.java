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
 * The class provides controller for client requests of application.
 * @author Nikolaev Ilya
 * @see HttpServlet
 */
@WebServlet(asyncSupported = true, name = "ServletHelper", urlPatterns = { "/controller" })
public class ServletHelper extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(ServletHelper.class);
	private static final long serialVersionUID = 1L;

	/**
     * @param request  request from client to get parameters
     * @param response response to client with parameters to work with on client side
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     * @throws ServletException if the request could not be handled
     * @see HttpServletRequest
     * @see HttpServletResponse
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	 /**
     * @param request  request from client to get parameters
     * @param response response to client with parameters to work with on client side
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     * @throws ServletException if the request could not be handled
     * @see HttpServletRequest
     * @see HttpServletResponse
     * 
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	 /**
     * processRequest
     * Defines command name  and directs request to command
     * If an error occurs, it is redirected to the error page.
     * @param request  request from client to get parameters to work with
     * @param response response to client with parameters to work with on client side
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     * @throws ServletException if the request could not be handled
     * @see CommandHelper
     */
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
