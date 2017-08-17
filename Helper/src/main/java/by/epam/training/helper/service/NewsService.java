package by.epam.training.helper.service;

import java.util.ArrayList;

import by.epam.training.helper.bean.News;
import by.epam.training.helper.service.exception.ServiceException;

public interface NewsService {

	ArrayList<News> getLastNews() throws ServiceException;

}
