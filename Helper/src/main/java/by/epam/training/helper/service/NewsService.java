package by.epam.training.helper.service;

import java.util.ArrayList;

import by.epam.training.helper.bean.PageItem;
import by.epam.training.helper.bean.News;
import by.epam.training.helper.service.exception.ServiceException;

public interface NewsService {

	ArrayList<News> getLastNews() throws ServiceException;

	void addNews(String titleNews, String contentNews) throws ServiceException;

	PageItem<News> getNewsPage(int pageNumber)throws ServiceException;

}
