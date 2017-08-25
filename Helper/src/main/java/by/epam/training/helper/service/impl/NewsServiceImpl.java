package by.epam.training.helper.service.impl;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.News;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.dao.NewsDAO;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.factory.DAOFactory;
import by.epam.training.helper.service.NewsService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.tools.Calculation;
import by.epam.training.helper.tools.FormatterString;
import by.epam.training.helper.tools.ItemManager;

public class NewsServiceImpl implements NewsService {
	private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);
	private final int NEWS_ON_PAGE = 10;
	private final int NEWS_ON_HOME_PAGE = 5;
	private int OFFSET = 0;
	@Override
	public ArrayList<News> getLastNews() throws ServiceException {
		ArrayList<News> news = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		NewsDAO newsDAO = daoFactory.getNewsDAO();
		try {
			news = newsDAO.getNewsWithLimit(OFFSET, NEWS_ON_HOME_PAGE);
		} catch (DAOException e) {
			logger.error(ErrorMessage.ERROR_NEWS);
			e.printStackTrace();
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
				logger.error(ErrorMessage.ERROR_ADD_NEWS);
				e.printStackTrace();
				throw new ServiceException();
			}
	}
	@Override
	public ItemManager<News> getNewsPage(int pageNumber) throws ServiceException {
		ItemManager<News> item = null;
		ArrayList<News> news = null;
		int offset = (pageNumber - 1) * NEWS_ON_PAGE;
		DAOFactory daoFactory = DAOFactory.getInstance();
		NewsDAO newsDAO = daoFactory.getNewsDAO();
		try {
			news = newsDAO.getNewsWithLimit(offset, NEWS_ON_PAGE);
			formattingNews(news);
			int countNews = newsDAO.getCountNews();
			int countPage = Calculation.pageCounting(countNews, NEWS_ON_PAGE);
			item = new ItemManager<News>(news, countPage);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException();
		}
		return item;
	}
	private void formattingNews(ArrayList<News> listNews) {
		for (News news : listNews) {
			String title = FormatterString.formattingTitle(news.getTitle());
			news.setTitle(title);
			String content = FormatterString.formattingContent(news.getContent());
			news.setContent(content);
		}
		
	}

}
