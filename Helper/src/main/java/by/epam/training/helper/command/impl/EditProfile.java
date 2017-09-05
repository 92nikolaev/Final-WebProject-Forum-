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
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.UserService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
/**
 * This command for editing user fields
 * @author Nikolaev Ilya
 * {@link Command}  invokes method execute() with the request , response  and return jsp question
 *
 */
public class EditProfile implements Command {
	private static final Logger logger = LogManager.getLogger(EditProfile.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String nameEdit = request.getParameter(ParameterName.USER_NAME);
		String surnameEdit = request.getParameter(ParameterName.USER_SURNAME);
		String emailEdit = request.getParameter(ParameterName.USER_EMAIL);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ParameterName.USER);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		try {
			userService.editProfile(user.getId(), nameEdit, surnameEdit, emailEdit);
			response.sendRedirect(Url.REDIRECT_USER_PROFILE_WITH_MESSAGE + SuccessMessage.EDIT_USER_FIELD);
		} catch (ServiceException e) {
			logger.error(e);
			response.sendRedirect(Url.REDIRECT_USER_PROFILE_WITH_MESSAGE  + ErrorMessage.ERROR_EDIT_USER_FIELD);
		}
	}

}
