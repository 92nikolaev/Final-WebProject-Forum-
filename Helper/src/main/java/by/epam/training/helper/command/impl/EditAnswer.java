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
import by.epam.training.helper.service.AnswerService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.utils.StringUtils;
import by.epam.training.helper.utils.StringParser;
/**
 * Command edit answer
 * @author Nikolaev Ilya
 * {@link Command}  invokes method execute() with the request , response  and return jsp question
 */
public class EditAnswer implements Command {
	private static final Logger logger = LogManager.getLogger(EditAnswer.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String answerIdParametr = request.getParameter(ParameterName.ANSWER_ID);
		String questionIdParamet = request.getParameter(ParameterName.QUESTION_ID);
		String answerContentParamet = request.getParameter(ParameterName.ANSWER_CONTENT);
		try{
			int answerId = StringParser.parseString(answerIdParametr);
			int questionId =  StringParser.parseString(questionIdParamet);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(ParameterName.USER);
			if(user != null){
				if(!StringUtils.isNullOrEmpty(answerContentParamet)){
					ServiceFactory serviceFactory = ServiceFactory.getInstance();
					AnswerService answerService = serviceFactory.getAnswerService();
					answerService.updateAnswerById(answerId, answerContentParamet);
					response.sendRedirect(Url.REDIRECT_QUESTION_PAGE+questionId);
				}else{
					request.getRequestDispatcher(Url.REDIRECT_EDIT_ANSWER+answerId).forward(request, response);
				}
			}else {
				logger.warn(ErrorMessage.USER_NOT_SIGN_IP);
				response.sendRedirect(Url.SIGN_IN);
			}
		}catch (NumberFormatException e) {
			logger.error(ErrorMessage.INVALID_ID);
			e.printStackTrace();
			throw new CommandException(ErrorMessage.INVALID_ID);
		} catch (ServiceException e) {
			logger.error(e);
			e.printStackTrace();
			throw new CommandException();
		}
	}

}
