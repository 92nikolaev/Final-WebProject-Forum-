package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.Answer;
import by.epam.training.helper.bean.Question;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.service.AnswerService;
import by.epam.training.helper.service.QuestionService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.tools.ItemManager;
import by.epam.training.helper.tools.NullOrEmpty;
import by.epam.training.helper.tools.StringInNumber;


public class GetQuestionById implements Command{
	private static final Logger logger = LogManager.getLogger(GetQuestionById.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String pageIndex = request.getParameter(ParameterName.NUMBER_PAGE);
		String questionIdParametr = request.getParameter(ParameterName.QUESTION_ID);
		int pageNumber = 1;
		int	questionId = 0;
		Question question = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		QuestionService questionService = serviceFactory.getQuestionService();
		if(!NullOrEmpty.isNullOrEmpty(pageIndex)){
			pageNumber = StringInNumber.parseString(pageIndex, pageNumber);
		}
		if(!NullOrEmpty.isNullOrEmpty(questionIdParametr)){
			questionId = StringInNumber.parseString(questionIdParametr, questionId);
			try {
				question = questionService.getQuestionById(questionId);
				if(question != null){
					 AnswerService answerService = serviceFactory.getAnswerService();
					 ItemManager<Answer> answerManager = null;
					 answerManager = answerService.getAnswerWithLimit(pageNumber, questionId);
					 request.setAttribute(ParameterName.QUESTION, question);
					 request.setAttribute(ParameterName.ANSWERS, answerManager.getItems());
					 request.setAttribute(ParameterName.CURRENT_PAGE, pageNumber);
					 request.getRequestDispatcher("question.jsp").forward(request, response);
				 }else{
					 request.getRequestDispatcher("controller?command=home").forward(request, response); 
				 }
			} catch (ServiceException e) {
				logger.error(e);
				e.printStackTrace();
				throw new CommandException(e);
			} 
		}else{
			 request.getRequestDispatcher("controller?command=home").forward(request, response); 
		}
		
	}

}
