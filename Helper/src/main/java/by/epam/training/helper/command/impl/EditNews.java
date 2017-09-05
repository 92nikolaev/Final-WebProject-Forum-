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
import by.epam.training.helper.constant.ErrorStatus;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.NewsService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.utils.StringParser;
import by.epam.training.helper.utils.StringUtils;
/**
 * Command edit news
 * @author Nikolaev Ilya
 * {@link Command}  invokes method execute() with the request , response  and return jsp question
 */
public class EditNews implements Command {
	private static final Logger logger = LogManager.getLogger(ShowNewsById.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String newsIdParametr = request.getParameter(ParameterName.NEWS_ID);
		String titleNews = request.getParameter(ParameterName.NEWS_TITLE);
		String contentNews = request.getParameter(ParameterName.NEWS_CONTENT);
		if(!StringUtils.isNullOrEmpty(titleNews) && !StringUtils.isNullOrEmpty(contentNews)){
			try{
				int newsId = StringParser.parseString(newsIdParametr);
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute(ParameterName.USER);
				if(user != null){
					if(user.getRole() == ParameterName.MODERATOR ||user.getRole() == ParameterName.ADMIN){
						ServiceFactory serviceFactory = ServiceFactory.getInstance();
						NewsService newsService = serviceFactory.getNewsService();
						newsService.editNews(newsId, titleNews, contentNews);
						response.sendRedirect("controller?command=show_news&message=news successfully edit&news_id="+ newsIdParametr);
					}else{
						logger.warn(ErrorMessage.USER_CAN_NOT_EDIT_NEWS);
						response.sendRedirect("controller?command=home&message=user can_not edit news");
					}
				}
			}catch (NumberFormatException e) {
				logger.error(ErrorMessage.ERROR_DETERMINETED_NEWS_ID + newsIdParametr);
				request.setAttribute(ParameterName.MESSAGE, ErrorStatus.NEWS_NOT_FOUND);
				request.getRequestDispatcher(Url.REDIRECT_LIST_NEWS_WITH_MESSAGE + ErrorStatus.NEWS_NOT_FOUND).forward(request, response);
			} catch (ServiceException e) {
				logger.error(ErrorMessage.ERROR_NOT_EDIT_NEWS);
				response.sendRedirect("controller?command=show_news&message=failed edit&news_id="+ newsIdParametr );
			}	
			}else {
				logger.warn(ErrorMessage.USER_NOT_SIGN_IP);
				response.sendRedirect(Url.SIGN_IN);
			}
		}

}
