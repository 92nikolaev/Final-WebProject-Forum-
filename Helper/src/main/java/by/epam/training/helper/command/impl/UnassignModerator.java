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
import by.epam.training.helper.utils.StringParser;
import by.epam.training.helper.validation.Validation;
/**
 * Command to remove the powers of a moderator
 * @author Nikolaev Ilya
 * {@link Command}  invokes method execute() with the request , response  and return jsp question
 */
public class UnassignModerator implements Command {
	private static final Logger logger = LogManager.getLogger(AssignModerator.class);
	byte USER_ROLE = 0;
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String userIdParametr = request.getParameter(ParameterName.USER_ID);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ParameterName.USER);
		if(user!=null && user.getRole() == ParameterName.ADMIN){
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			try{
				int userId = StringParser.parseString(userIdParametr);
				if(user.getId() != userId){
					if(Validation.isValidationId(userId)){
						userService.assignModeratorOrUser(userId, USER_ROLE);
						response.sendRedirect(Url.SHOW_ALL_USER_WITH_MESSAGE + SuccessMessage.USER_ROLE);
					}else{
						response.sendRedirect(Url.SHOW_ALL_USER_WITH_MESSAGE + ErrorMessage.ERROR_USER_INDEFINED);
					}
				}else {
					logger.error(ErrorMessage.ERROR_USER_ASSIGN_MODERATOR_HIMSELF);
					response.sendRedirect(Url.SHOW_ALL_USER_WITH_MESSAGE + ErrorMessage.ERROR_USER_ASSIGN_MODERATOR_HIMSELF);
				}
			}catch (NumberFormatException e) {
				logger.error(ErrorMessage.ERROR_DETERMIN_ID);
				response.sendRedirect(Url.SHOW_ALL_USER_WITH_MESSAGE + ErrorMessage.ERROR_USER_INDEFINED);
			} catch (ServiceException e) {
				logger.error(ErrorMessage.ERROR_USER_ASSIGN_ROLE);
				response.sendRedirect(Url.SHOW_ALL_USER_WITH_MESSAGE + ErrorMessage.ERROR_USER_ASSIGN_ROLE);
			}	
		}else{
			response.sendRedirect(Url.SIGN_IN);
		}
		}


}
