package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.PageItem;
import by.epam.training.helper.bean.News;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.NewsService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.utils.StringUtils;
import by.epam.training.helper.utils.StringParser;
/**
 * Command to display a list of all news.
 * @author Nikolaev Ilya
 * {@link Command}  invokes method execute() with the request , response  and return jsp question
 */
public class ShowNews implements Command {
	private static final Logger logger = LogManager.getLogger(ShowNews.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String pageIndex = request.getParameter(ParameterName.NUMBER_PAGE);
		int pageNumber = 1;
		PageItem<News> itemManager = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		NewsService newsService = serviceFactory.getNewsService();
		if(!StringUtils.isNullOrEmpty(pageIndex)){
			pageNumber = StringParser.parseString(pageIndex, pageNumber);
		}
		try {
			itemManager = newsService.getNewsPage(pageNumber);
			request.setAttribute(ParameterName.NEWS, itemManager.getItems());
			request.setAttribute(ParameterName.AMONT_PAGE, itemManager.getPageCount());
			request.setAttribute(ParameterName.MESSAGE, request.getParameter(ParameterName.MESSAGE));
			request.setAttribute(ParameterName.CURRENT_PAGE, pageNumber);
			request.getRequestDispatcher(Url.LIST_NEWS).forward(request, response);
		} catch (ServiceException e) {
			logger.error(ErrorMessage.ERROR_SHOW_LIST_NEWS);
			throw new CommandException();
		}
	}

}
