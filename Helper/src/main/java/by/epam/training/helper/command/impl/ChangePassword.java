package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.SuccessMessage;
import by.epam.training.helper.service.UserService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;


public class ChangePassword implements Command {
	private static final Logger logger = LogManager.getLogger(ChangePassword.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, CommandException, IOException {
		String oldPassword = request.getParameter(ParameterName.OLD_PASSWORD);
		String newPassword = request.getParameter(ParameterName.NEW_PASSWORD);
		String vereficationPassword = request.getParameter(ParameterName.NEW_VERIFICATION_PASSWORD);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ParameterName.USER);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		try {
			userService.updatePassword(user.getId(), user.getLogin(), oldPassword, newPassword, vereficationPassword);
			response.sendRedirect("controller?command=user_profile&message="+SuccessMessage.UPDATE_PASSWORD);
		} catch (ServiceException e) {
			logger.error(e);
			response.sendRedirect("controller?command=user_profile&message="+ErrorMessage.ERROR_CHANGE_PASSWORD);
		}

	}

}
