package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.Answer;
import by.epam.training.helper.bean.User;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.AnswerService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.utils.StringParser;
/**
 * Command get page edit answer
 * @author Nikolaev Ilya
 * {@link Command}  invokes method execute() with the request , response  and return jsp question
 */
public class GetPageEditAnswer implements Command {
	private static final Logger logger = LogManager.getLogger(GetPageEditAnswer.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String answerIdParametr = request.getParameter(ParameterName.ANSWER_ID);
		try{
			int answerId = StringParser.parseString(answerIdParametr);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(ParameterName.USER);
			if(user != null){
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				AnswerService answerService = serviceFactory.getAnswerService();
				Answer answer = answerService.getAnswerById(answerId);
				if(answer.getUserId() == user.getId()){
					request.setAttribute(ParameterName.ANSWER, answer);
					request.getRequestDispatcher(Url.EDIT_ANSWER).forward(request, response);
				}else{
					logger.error(ErrorMessage.ERROR_ACCESS);
					throw new CommandException(ErrorMessage.ERROR_ACCESS);
				}
			}else {
				response.sendRedirect(Url.SIGN_IN);
			}
		}catch (NumberFormatException e) {
			logger.error(ErrorMessage.INVALID_ID);
			throw new CommandException();
		} catch (ServiceException e) {
			logger.error(e);
			throw new CommandException();
		}
	}

}
