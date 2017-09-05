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
import by.epam.training.helper.constant.ErrorMessageCommand;
import by.epam.training.helper.constant.ErrorStatus;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.SuccessMessage;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.MarkService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
/**
 * Evaluate the user for his answer
 * @author Nikolaev Ilya
 * {@link Command}  invokes method execute() with the request , response  and return jsp question
 *
 */
public class EvaluateAnswer implements Command {
	private static final Logger logger = LogManager.getLogger(EvaluateAnswer.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String markValueParamet = request.getParameter(ParameterName.MARK_VALUE);
		System.out.println(markValueParamet + " markValueParamet");
		String answerIdParametr = request.getParameter(ParameterName.ANSWER_ID);
		String questionIdParametr = request.getParameter(ParameterName.QUESTION_ID);
		try{
			int mark = Integer.parseInt(markValueParamet);
			int answerId = Integer.parseInt(answerIdParametr);
			int questionId = Integer.parseInt(questionIdParametr);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(ParameterName.USER);
			if(user != null){
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				MarkService markService = serviceFactory.getMarkService();
				markService.addMark(user, mark, answerId);
				System.out.println(String.format(Url.REDIRECT_QUESTION_PAGE_WITH_MESSAGE, questionId, SuccessMessage.RATE_ANSWER));
				response.sendRedirect(String.format(Url.REDIRECT_QUESTION_PAGE_WITH_MESSAGE, questionId, SuccessMessage.RATE_ANSWER));
			}else {
				logger.error(ErrorStatus.ERROR_NOT_SIGN_IN);
				response.sendRedirect(Url.SIGN_IN);
			}
		} catch (NumberFormatException e) {
			logger.error(ErrorMessageCommand.ERROR_DETERMINE_MARK + markValueParamet);
			response.sendRedirect(String.format(Url.REDIRECT_QUESTION_PAGE_WITH_MESSAGE, questionIdParametr, ErrorStatus.ERROR_DETERMINE_MARK));
		} catch (ServiceException e) {
			logger.error(ErrorMessageCommand.ERROR_EVALUATE_USER);
			response.sendRedirect(String.format(Url.REDIRECT_QUESTION_PAGE_WITH_MESSAGE, questionIdParametr, ErrorStatus.ERROR_DETERMINE_MARK));
		}
		

	}

}
