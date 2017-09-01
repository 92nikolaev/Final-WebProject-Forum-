package by.epam.training.helper.service.impl;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.PageItem;
import by.epam.training.helper.bean.News;
import by.epam.training.helper.constant.ErrorMessageService;
import by.epam.training.helper.dao.NewsDAO;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.factory.DAOFactory;
import by.epam.training.helper.service.NewsService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.utils.PaginationUtils;
import by.epam.training.helper.utils.StringConverter;

public class NewsServiceImpl implements NewsService {
	private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);
	private final int NEWS_ON_PAGE = 10;
	private final int NEWS_ON_HOME_PAGE = 10;
	private int OFFSET = 0;
	@Override
	public ArrayList<News> getLastNews() throws ServiceException {
		ArrayList<News> news = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		NewsDAO newsDAO = daoFactory.getNewsDAO();
		try {
			news = newsDAO.getNewsWithLimit(OFFSET, NEWS_ON_HOME_PAGE);
		} catch (DAOException e) {
			logger.error(ErrorMessageService.ERROR_NEWS + e);
			news = new ArrayList<>(1);
		}
		return news;
	}
	@Override
	public void addNews(String titleNews, String contentNews) throws ServiceException {
			DAOFactory daoFactory = DAOFactory.getInstance();
			NewsDAO newsDAO = daoFactory.getNewsDAO();
			try {
				newsDAO.addNews(titleNews, contentNews);
			} catch (DAOException e) {
				logger.error(ErrorMessageService.ERROR_ADD_NEWS + e);
				throw new ServiceException(ErrorMessageService.ERROR_ADD_NEWS);
			}
	}
	@Override
	public PageItem<News> getNewsPage(int pageNumber) throws ServiceException {
		PageItem<News> item = null;
		ArrayList<News> news = null;
		int offset = (pageNumber - 1) * NEWS_ON_PAGE;
		DAOFactory daoFactory = DAOFactory.getInstance();
		NewsDAO newsDAO = daoFactory.getNewsDAO();
		try {
			news = newsDAO.getNewsWithLimit(offset, NEWS_ON_PAGE);
			formattingNews(news);
			int countNews = newsDAO.getCountNews();
			int countPage = PaginationUtils.pageCounting(countNews, NEWS_ON_PAGE);
			item = new PageItem<News>(news, countPage);
		} catch (DAOException e) {
			logger.error(ErrorMessageService.ERROR_GET_DATA + e);
			throw new ServiceException(ErrorMessageService.ERROR_GET_DATA);
		}
		return item;
	}
	private void formattingNews(ArrayList<News> listNews) {
		for (News news : listNews) {
			String title = StringConverter.formattingTitle(news.getTitle());
			news.setTitle(title);
			String content = StringConverter.formattingContent(news.getContent());
			news.setContent(content);
		}
		
	}

}
