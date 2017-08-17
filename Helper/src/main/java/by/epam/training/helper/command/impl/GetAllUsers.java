package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.service.UserService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.tools.ItemManager;
import by.epam.training.helper.tools.NullOrEmpty;
import by.epam.training.helper.tools.StringInNumber;


public class GetAllUsers implements Command {
	private static final Logger logger = LogManager.getLogger(GetAllUsers.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, CommandException {
		String pageIndex = request.getParameter(ParameterName.NUMBER_PAGE);
		int pageNumber = 1;
		ItemManager<User> itemManager = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		if(!NullOrEmpty.isNullOrEmpty(pageIndex)){
			pageNumber = StringInNumber.parseString(pageIndex, pageNumber);
		}
		try {
			itemManager = userService.getUsersPage(pageNumber);
			request.setAttribute(ParameterName.USERS, itemManager.getItems());
			request.setAttribute(ParameterName.AMONT_PAGE, itemManager.getPageCount());
			request.getRequestDispatcher("users.jsp").forward(request, response);
		} catch (ServiceException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
