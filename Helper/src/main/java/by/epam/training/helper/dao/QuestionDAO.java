package by.epam.training.helper.dao;

import java.util.ArrayList;

import by.epam.training.helper.bean.Question;
import by.epam.training.helper.dao.exception.DAOException;
/**
 * Interface Question DAO
 * @author Nikolaev Ilya
 */
public interface QuestionDAO {
	/**
	 * Method selects list of questions with limit and return list sorted by create date descending
	 * @param offset offset of the first question in selected list (0 means from the beginning)
	 * @param itemOnPage itemOnPage - size of questions per page
	 * @return list {@link Question} return list sorted by create date descending
	 * @throws DAOException
	 */
	ArrayList<Question> getQuestionsWithLimit(int offset, int itemOnPage) throws DAOException;
	/**
	 * Method select amount of questions in database 
	 * @return amount of questions
	 * @throws DAOException
	 */
	int getAmountQuestions()throws DAOException;

	/**
	 * The method selects questions by the user's search query and return list sorted by create date descending.
	 * @param offset offset of the first question in selected list (0 means from the beginning)
	 * @param itemOnPage itemOnPage - size of questions per page
	 * @param searchQuestion searchQuestion - user search query
	 * @return list {@link Question} return list sorted by create date descending
	 * @throws DAOException
	 */
	ArrayList<Question> getSearchQuestionWithLimit(int offset, int itemOnPage, String searchQuestion)throws DAOException;
	
	/**
	 * Method select amount of questions by the user's search query in database
	 * @param searchQuestion searchQuestion - user search query
	 * @return amount of questions
	 * @throws DAOException
	 */
	int getAmountSearchQuestion(String searchQuestion) throws DAOException;
	/**
	 * Method select list of questions belonging to user with specified user id and
	 * list sorted by create date descending
	 * @param userId ID user to search for questions
	 * @return list of {@link Question}
	 * @throws DAOException
	 */
	ArrayList<Question> getUserQuestion(int userId)throws DAOException;
	/**
	 * Method add new question into database
	 * @param userId
	 * @param title
	 * @param content
	 * @throws DAOException
	 */
	void addQuestion(int userId, String title, String content)throws DAOException;
	/**
	 * Method select question by question ID 
	 * @param questionId 
	 * @return {@link Question}
	 * @throws DAOException
	 */
	Question getQestionById(int questionId)throws DAOException;
	/**
	 * Updates question in the database
	 * @param questionId
	 * @param questionTitle
	 * @param questionContent
	 * @throws DAOException
	 */
	void updateQuestionById(int questionId, String questionTitle, String questionContent)throws DAOException;


}
