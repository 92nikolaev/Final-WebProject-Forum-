package by.epam.training.helper.dao;

import java.util.ArrayList;

import by.epam.training.helper.bean.Answer;
import by.epam.training.helper.dao.exception.DAOException;
/**
 * Interface Answer DAO
 * @author Nikolaev Ilya
 */
public interface AnswerDAO {
	
	/**
	 * Method selects a list of answers that belong to a specific question
	 * @param questionId
	 * @param offset
	 * @param itemOnPage
	 * @return list returns sorted by the date of their creation
	 * @throws DAOException
	 */
	ArrayList<Answer> getAnswerWithLimit(int questionId, int offset, int itemOnPage) throws DAOException;

	/**
	 * Method counts the number of answers to a specific question ID
	 * @param questionId
	 * @return amount of answers to a specific question ID
	 * @throws DAOException
	 */
	int getAmountAnswers(int questionId)throws DAOException;
	/**
	 * Method inserts new answer into database
	 * @param answer
	 * @throws DAOException
	 */
	void addAnswer(Answer answer)throws DAOException;
	/**
	 * Method find the answer by his ID
	 * @param answerId
	 * @return {@link Answer}
	 * @throws DAOException
	 */
	Answer answerById(int answerId)throws DAOException;

	/**
	 * Method updates answer in the database
	 * @param answerId
	 * @param answerContent
	 * @throws DAOException
	 */
	void updateAnswerById(int answerId, String answerContent)throws DAOException;

}
