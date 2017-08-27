package by.epam.training.helper.dao;

import by.epam.training.helper.bean.Mark;
import by.epam.training.helper.dao.exception.DAOException;
/**
 * Interface Mark DAO
 * @author Nikolaev Ilya
 */
public interface MarkDAO {
	/**
	 * Method select all of the estimates for the answer on the user ID and answer ID
	 * @param userId
	 * @param answerId
	 * @return {@link Mark}
	 * @throws DAOException
	 */
	Mark getMarkByUserIdAnswerId(int userId, int answerId) throws DAOException;
	/**
	 * Method add mark into the database
	 * @param userId
	 * @param answerId
	 * @param newMark
	 * @throws DAOException
	 */
	void addMark(int userId, int answerId, int newMark)throws DAOException;
	/**
	 * Method Updates mark in the database
	 * @param markId
	 * @param newMark
	 * @throws DAOException
	 */
	void updateMark(int markId, int newMark)throws DAOException;


}
