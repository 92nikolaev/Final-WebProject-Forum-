package by.epam.training.helper.command.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.Question;
import by.epam.training.helper.bean.User;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.QuestionService;
import by.epam.training.helper.service.UserService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
/**
 * The class is used to enter personal area and display personal information and asked a question
 * @author Nikolaev Ilya
 * {@link Command}  invokes method execute() with the request , response  and return jsp question
 */
public class GetUserProfile implements Command {
	private static final Logger logger = LogManager.getLogger(GetUserProfile.class);
	/**
	 *  display personal information and asked a question
	 *	@param request - request from client to get parameters to work with it
	 *  @param response - send response to client with parameters to work with on client side
	 *  @throws IOException  
     * 	@throws ServletException
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ParameterName.USER);
		if(user != null){
			ArrayList<Question> questions = null;
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			QuestionService questionService = serviceFactory.getQuestionService();
			UserService userService = serviceFactory.getUserService();
			
			try {
				user = userService.getUserById(user.getId());
				questions = questionService.getUserQuestion(user.getId());
				request.setAttribute(ParameterName.USER, user);
				request.setAttribute(ParameterName.QUESTIONS, questions);
				request.setAttribute(ParameterName.MESSAGE, request.getParameter(ParameterName.MESSAGE));
				request.getRequestDispatcher(Url.PROFILE).forward(request, response);
			} catch (ServiceException e) {
				logger.error(ErrorMessage.ERROR_ENTER_CABINET);
				response.sendRedirect(Url.REDIRECT_SIGN_IN);
			}
		}else{
			logger.error(ErrorMessage.ERROR_ENTER_CABINET);
			response.sendRedirect(Url.SIGN_IN);
		}
		
	}
}
