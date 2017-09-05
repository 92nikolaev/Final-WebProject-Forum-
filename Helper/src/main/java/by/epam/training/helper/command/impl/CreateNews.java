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
import by.epam.training.helper.utils.StringUtils;
/**
 * The command to create new news
 * @author Nikolaev Ilya
 * {@link Command}  invokes method execute() with the request , response  and return jsp question
 */
public class CreateNews implements Command {
	private static final Logger logger = LogManager.getLogger(CreateNews.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String titleNews = request.getParameter(ParameterName.NEWS_TITLE);
		String contentNews = request.getParameter(ParameterName.NEWS_CONTENT);
		if(!StringUtils.isNullOrEmpty(titleNews) && !StringUtils.isNullOrEmpty(contentNews)){
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(ParameterName.USER);
			if(user != null){
				if(user.getRole() == ParameterName.MODERATOR ||user.getRole() == ParameterName.ADMIN){
					ServiceFactory serviceFactory = ServiceFactory.getInstance();
					NewsService newsService = serviceFactory.getNewsService();
					try {
						newsService.addNews(titleNews, contentNews);
						response.sendRedirect(Url.REDIRECT_SHOW_ALL_NEWS_SUCCESS_ADD);
					} catch (ServiceException e) {
						logger.error(ErrorMessage.ERROR_NOT_ADD_NEWS);
						e.printStackTrace();
						response.sendRedirect(Url.REDIRECT_FAILED_ADD_NEWS);
					}	
				}else{
					logger.warn(ErrorMessage.USER_CAN_NOT_CREATE_NEWS);
					response.sendRedirect(Url.REDIRECT_LIST_NEWS_WITH_MESSAGE+ErrorMessage.USER_CAN_NOT_CREATE_NEWS);
				}
			}else {
				logger.warn(ErrorMessage.USER_NOT_SIGN_IP);
				response.sendRedirect(Url.SIGN_IN);
			}
		}else{
			logger.warn("NEWS_EMPTY");
			response.sendRedirect(Url.REDIRECT_SHOW_ALL_NEWS_NOT_ADD_EMPTY);
		}
		
		

	}

}
