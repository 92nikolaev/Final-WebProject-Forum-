﻿package by.epam.training.helper.command.impl;

import static by.epam.training.helper.utils.StringUtils.isNullOrEmpty;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.PageItem;
import by.epam.training.helper.bean.News;
import by.epam.training.helper.bean.Question;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.NewsService;
import by.epam.training.helper.service.QuestionService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.utils.StringParser;
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
		PageItem<Question> itemManager = null;
		ArrayList<News> listNews = null;
		if(!isNullOrEmpty(pageIndex)){
			pageNumber = StringParser.parseString(pageIndex, pageNumber);
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
			if(!isNullOrEmpty(searchQuestion)){
				request.setAttribute(ParameterName.SEARCH_QUESTION,searchQuestion);
			}
			request.getRequestDispatcher(Url.HOME).forward(request, response);
		} catch (ServiceException e) {
			logger.error(ErrorMessage.ERROR_HOME_PAGE_NOT_DISPLAY, e);
			throw new CommandException(ErrorMessage.ERROR_HOME_PAGE_NOT_DISPLAY);
		}
	}
	
}
