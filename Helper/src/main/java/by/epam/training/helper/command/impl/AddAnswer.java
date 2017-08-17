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
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.service.AnswerService;
import by.epam.training.helper.service.QuestionService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.tools.NullOrEmpty;
import by.epam.training.helper.tools.StringInNumber;

public class AddAnswer implements Command {
	private static final Logger logger = LogManager.getLogger(AddAnswer.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String answerContent = request.getParameter(ParameterName.ANSWER_CONTENT);
		String questionIdParametr = request.getParameter(ParameterName.QUESTION_ID);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ParameterName.USER);
		try{
			int	questionId = StringInNumber.parseString(questionIdParametr);
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			if(!NullOrEmpty.isNullOrEmpty(questionIdParametr)&&!NullOrEmpty.isNullOrEmpty(answerContent)){
				QuestionService questionService = serviceFactory.getQuestionService();
				if(questionService.isExistQuestion(questionId)){
					AnswerService answerService = serviceFactory.getAnswerService();
					Answer answer = getAnswer(user, answerContent, questionId);
					answerService.addAnswer(answer);
					request.getRequestDispatcher("controller?command=show_question&qestion_id=" + questionId).forward(request, response); 
				}else{
					request.getRequestDispatcher("controller?command=home").forward(request, response); 
				}
			}else{
				request.getRequestDispatcher("controller?command=home").forward(request, response); 
			}
		}catch (NumberFormatException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (ServiceException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	private Answer getAnswer(User user, String answerContent, int questionId) {
		Answer answer = new Answer();
		answer.setUserId(user.getId());
		answer.setQuestionId(questionId);
		answer.setContent(answerContent);
		return answer;
	}
}
			
		
		
		
		

	