package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.PageItem;
import by.epam.training.helper.bean.User;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.UserService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.utils.StringUtils;
import by.epam.training.helper.utils.StringParser;

/**
 * Command to get a list of all users,sends the specified page
 * @author Nikolaev Ilya
 */
public class GetAllUsers implements Command {
	private static final Logger logger = LogManager.getLogger(GetAllUsers.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, CommandException {
		String pageIndex = request.getParameter(ParameterName.NUMBER_PAGE);
		String message = request.getParameter(ParameterName.MESSAGE);
		int pageNumber = 1;
		PageItem<User> itemManager = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		if(!StringUtils.isNullOrEmpty(pageIndex)){
			pageNumber = StringParser.parseString(pageIndex, pageNumber);
		}
		try {
			itemManager = userService.getUsersPage(pageNumber);
			request.setAttribute(ParameterName.MESSAGE, message);
			request.setAttribute(ParameterName.USERS, itemManager.getItems());
			request.setAttribute(ParameterName.AMONT_PAGE, itemManager.getPageCount());
			request.setAttribute(ParameterName.CURRENT_PAGE, pageNumber);
			request.getRequestDispatcher(Url.USERS).forward(request, response);
		} catch (ServiceException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
