package by.epam.training.helper.dao;

import java.util.ArrayList;

import by.epam.training.helper.bean.News;
import by.epam.training.helper.dao.exception.DAOException;
/**
 * Interface News DAO
 * @author Nikolaev Ilya
 */
public interface NewsDAO {
	/**
	 * Method returns a list of news
	 * @param offset
	 * @param newsOnPage
	 * @return List {@link News}
	 * @throws DAOException
	 */
	ArrayList<News> getNewsWithLimit(int offset, int newsOnPage) throws DAOException;

	/**
	 * Method for adding news.
	 * @param titleNews
	 * @param contentNews
	 * @throws DAOException
	 */
	void addNews(String titleNews, String contentNews)throws DAOException;

	/**
	 * The method counts the amount of news
	 * @return count news in the database
	 * @throws DAOException
	 */
	int getCountNews()throws DAOException;

}
