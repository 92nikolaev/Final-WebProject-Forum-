package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.Url;
/**
 * Class is used for sign out.
 * @author Nikolaev Ilya
 * {@link Command}  invokes method execute() with the request , response  and return jsp question
 */
public class SignOut implements Command{
	private static final Logger logger = LogManager.getLogger(SignOut.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
			response.sendRedirect(Url.REDIRECT_HOME_PAGE);	
		}else{
			logger.error(ErrorMessage.ERROR_SIGN_UOT);
			response.sendRedirect(Url.REDIRECT_HOME_PAGE);	
		}
	}

}
