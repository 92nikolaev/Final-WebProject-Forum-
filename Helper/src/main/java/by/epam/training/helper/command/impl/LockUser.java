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
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.UserService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.tools.StringInNumber;
import by.epam.training.helper.validation.Validation;

public class LockUser implements Command {
	private static final Logger logger = LogManager.getLogger(LockUser.class);
	byte BLOCK_STATUS = 0;
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
				int userId = StringInNumber.parseString(userIdParametr);
				if(Validation.isValidationId(userId)){
					userService.lockUser(userId, BLOCK_STATUS);
					response.sendRedirect("controller?command=showAllUsers");
				}else{
					//response.sendRedirect("controller?command=showAllUsers&message=Sorry this user dont lock");
				}
			}catch (NumberFormatException e) {
				logger.error(ErrorMessage.ERROR_DETERMIN_ID);
				//response.sendRedirect("controller?command=showAllUsers&message=Sorry this user dont lock");
			} catch (ServiceException e) {
				logger.error(ErrorMessage.ERROR_LOCK_USER);
				//response.sendRedirect("controller?command=showAllUsers&message=Sorry this user dont lock");
			}	
		}else{
			response.sendRedirect(Url.SIGN_IN);
		}
	}
}
