package by.epam.training.helper.dao;

import java.util.ArrayList;

import by.epam.training.helper.bean.News;
import by.epam.training.helper.dao.exception.DAOException;

public interface NewsDAO {

	ArrayList<News> getNewsWithLimit(int offset, int newsOnPage) throws DAOException;

	void addNews(String titleNews, String contentNews)throws DAOException;

	int getCountNews()throws DAOException;

}
