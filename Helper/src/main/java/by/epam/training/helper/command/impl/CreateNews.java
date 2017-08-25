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
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.NewsService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.tools.NullOrEmpty;

public class CreateNews implements Command {
	private static final Logger logger = LogManager.getLogger(CreateNews.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String titleNews = request.getParameter(ParameterName.NEWS_TITLE);
		String contentNews = request.getParameter(ParameterName.NEWS_CONTENT);
		if(!NullOrEmpty.isNullOrEmpty(titleNews) && !NullOrEmpty.isNullOrEmpty(contentNews)){
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(ParameterName.USER);
			if(user != null){
				if(user.getRole() == ParameterName.ADMIN){
					ServiceFactory serviceFactory = ServiceFactory.getInstance();
					NewsService newsService = serviceFactory.getNewsService();
					try {
						newsService.addNews(titleNews, contentNews);
						response.sendRedirect("controller?command=show_all_news&message=news_successfully_add");
					} catch (ServiceException e) {
						logger.error(ErrorMessage.ERROR_NOT_ADD_NEWS);
						e.printStackTrace();
						response.sendRedirect("controller?command=home&message=news_not_add");
					}	
				}else{
					logger.warn(ErrorMessage.USER_CAN_NOT_CREATE_NEWS);
					response.sendRedirect("controller?command=home&message=user_can_not_create_news");
				}
			}else {
				logger.warn(ErrorMessage.USER_NOT_SIGN_IP);
				response.sendRedirect(Url.SIGN_IN);
			}
		}
		
		

	}

}
