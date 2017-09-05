package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.News;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.ErrorStatus;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.NewsService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.utils.StringParser;
import by.epam.training.helper.utils.StringUtils;
import by.epam.training.helper.validation.Validation;
/**
 * Command to display news by ID.
 * @author Nikolaev Ilya
 * {@link Command}  invokes method execute() with the request , response  and return jsp question
 */
public class ShowNewsById implements Command {
	private static final Logger logger = LogManager.getLogger(ShowNewsById.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String message = request.getParameter(ParameterName.MESSAGE);
		String newsIdParametr = request.getParameter(ParameterName.NEWS_ID);
		News news = null;
		try{
			int newsId = StringParser.parseString(newsIdParametr);
			if(Validation.isValidationId(newsId)){
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				NewsService newsService = serviceFactory.getNewsService();
				news = newsService.getNewsById(newsId);
				if(news != null){
					if(!StringUtils.isNullOrEmpty(message)){
						request.setAttribute(ParameterName.MESSAGE, message);
					}
					request.setAttribute(ParameterName.NEWS, news);
					request.getRequestDispatcher(Url.NEWS).forward(request, response);
				}else{
					request.setAttribute(ParameterName.MESSAGE, ErrorStatus.NEWS_NOT_FOUND);
					request.getRequestDispatcher(Url.REDIRECT_LIST_NEWS_WITH_MESSAGE + ErrorStatus.NEWS_NOT_FOUND).forward(request, response);
				}
			}else{
				request.setAttribute(ParameterName.MESSAGE, ErrorStatus.INVALID_ID + newsIdParametr);
				request.getRequestDispatcher(Url.REDIRECT_LIST_NEWS_WITH_MESSAGE + ErrorStatus.NEWS_NOT_FOUND).forward(request, response);
			}
		}catch (NumberFormatException e) {
				logger.error(ErrorMessage.ERROR_DETERMINETED_NEWS_ID + newsIdParametr);
				request.setAttribute(ParameterName.MESSAGE, ErrorStatus.NEWS_NOT_FOUND);
				request.getRequestDispatcher(Url.REDIRECT_LIST_NEWS_WITH_MESSAGE + ErrorStatus.NEWS_NOT_FOUND).forward(request, response);
		} catch (ServiceException e) {
				request.setAttribute(ParameterName.MESSAGE, ErrorStatus.NEWS_NOT_FOUND);
				request.getRequestDispatcher(Url.REDIRECT_LIST_NEWS_WITH_MESSAGE + ErrorStatus.NEWS_NOT_FOUND).forward(request, response);
		}	
	}
}
