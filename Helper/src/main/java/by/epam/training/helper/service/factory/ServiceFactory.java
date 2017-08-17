package by.epam.training.helper.service.factory;

import by.epam.training.helper.service.AnswerService;
import by.epam.training.helper.service.MarkService;
import by.epam.training.helper.service.NewsService;
import by.epam.training.helper.service.QuestionService;
import by.epam.training.helper.service.UserService;
import by.epam.training.helper.service.impl.AnswerServiceImpl;
import by.epam.training.helper.service.impl.MarkServiceImpl;
import by.epam.training.helper.service.impl.NewsServiceImpl;
import by.epam.training.helper.service.impl.QuestionServiceImpl;
import by.epam.training.helper.service.impl.UserServiceImpl;

/**
 * @author Nikolaev Ilya
 *
 */
public class ServiceFactory {
	private static ServiceFactory instance;
	private UserService userService = new UserServiceImpl();
	private QuestionService questionService = new QuestionServiceImpl();
	private NewsService newsService = new NewsServiceImpl();
	private AnswerService answerService = new AnswerServiceImpl();
	private MarkService markService = new MarkServiceImpl();
	
	private ServiceFactory(){}

	public static ServiceFactory getInstance() {
		if(instance == null){
			instance = new ServiceFactory();
		}
		return instance;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public UserService getUserService() {
		return userService;
	}

	public AnswerService getAnswerService() {
		return answerService;
	}

	public MarkService getMarkService() {
		return markService;
	}
	
}
