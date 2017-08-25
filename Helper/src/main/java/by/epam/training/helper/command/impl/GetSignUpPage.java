package by.epam.training.helper.command.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.News;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.NewsService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.tools.NullOrEmpty;

public class GetSignUpPage implements Command {
	private static final Logger logger = LogManager.getLogger(GetSignUpPage.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		ArrayList<News> listNews = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		NewsService newsService = serviceFactory.getNewsService();
		try {
			listNews = newsService.getLastNews();
			request.setAttribute(ParameterName.NEWS, listNews);
			String errorStatus = request.getParameter(ParameterName.ERROR_STATUS);
			if(!NullOrEmpty.isNullOrEmpty(errorStatus)){
				request.setAttribute(ParameterName.ERROR_STATUS, true);
			}
			request.getRequestDispatcher(Url.SIGN_UP).forward(request, response);
		} catch (ServiceException e) {
			logger.error(e);
			response.sendRedirect(Url.SIGN_UP);
		}
	}

}
