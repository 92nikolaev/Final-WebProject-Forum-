package by.epam.training.helper.command.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.News;
import by.epam.training.helper.bean.Question;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.NewsService;
import by.epam.training.helper.service.QuestionService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.tools.ItemManager;
import static by.epam.training.helper.tools.NullOrEmpty.isNullOrEmpty;
import by.epam.training.helper.tools.StringInNumber;
/**
 *  @author Nikolaev Ilya
 *	Home page, with all questions, you can also search for questions, show the latest news
 */
public class GetHomePage implements Command {
	
	private static final Logger logger = LogManager.getLogger(GetHomePage.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
		String pageIndex = request.getParameter(ParameterName.NUMBER_PAGE);
		String searchQuestion = request.getParameter(ParameterName.SEARCH_QUESTION);
		String message = request.getParameter(ParameterName.MESSAGE);
		int pageNumber = 1;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		QuestionService questionService = serviceFactory.getQuestionService();
		ItemManager<Question> itemManager = null;
		ArrayList<News> listNews = null;
		if(!isNullOrEmpty(pageIndex)){
			pageNumber = StringInNumber.parseString(pageIndex, pageNumber);
		}
		try{ 
			if(isNullOrEmpty(searchQuestion)){
				itemManager = questionService.getQuestionsPage(pageNumber);
			}else{
				itemManager = questionService.getSearchPage(searchQuestion, pageNumber);
			}
			NewsService newsService = serviceFactory.getNewsService();
			listNews = newsService.getLastNews();
			request.setAttribute(ParameterName.QUESTIONS, itemManager.getItems());
			request.setAttribute(ParameterName.AMONT_PAGE, itemManager.getPageCount());
			request.setAttribute(ParameterName.CURRENT_PAGE, pageNumber);
			request.setAttribute(ParameterName.NEWS, listNews);
			if(!isNullOrEmpty(message)){
				request.setAttribute(ParameterName.MESSAGE, message);
			}
			request.getRequestDispatcher(Url.HOME).forward(request, response);
		} catch (ServiceException e) {
			logger.error(e);
			e.printStackTrace();
			throw new CommandException(e);
		}
	}
	
}
