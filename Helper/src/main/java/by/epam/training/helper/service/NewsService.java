package by.epam.training.helper.service;

import java.util.ArrayList;

import by.epam.training.helper.bean.News;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.tools.ItemManager;

public interface NewsService {

	ArrayList<News> getLastNews() throws ServiceException;

	void addNews(String titleNews, String contentNews) throws ServiceException;

	ItemManager<News> getNewsPage(int pageNumber)throws ServiceException;

}
