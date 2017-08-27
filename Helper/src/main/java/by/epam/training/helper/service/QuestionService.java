package by.epam.training.helper.service;

import java.util.ArrayList;

import by.epam.training.helper.bean.Question;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.tools.ItemManager;

public interface QuestionService {
	/**
	 * Select questions for the page and the number of pages
	 * @param pageNumber page number to find
	 * @return {@link ItemManager} current page and the number of pages
	 * @throws ServiceException
	 */
	ItemManager<Question> getQuestionsPage(int pageNumber) throws ServiceException;
	/**
	 * Select questions of searched question user for the page and the number of pages
	 * @param searchQuestion search query
	 * @param pageNumber page number to find
	 * @return {@link ItemManager} current search page and the number of pages
	 * @throws ServiceException
	 */
	ItemManager<Question> getSearchPage(String searchQuestion, int pageNumber)throws ServiceException;
	/**
	 * Selects all user questions
	 * @param userId
	 * @return list of user questions
	 * @throws ServiceException
	 */
	ArrayList<Question> getUserQuestion(int userId) throws ServiceException;
	
	/**
	 * Adding a new question
	 * @param userId
	 * @param questionTitle
	 * @param questionContent
	 * @throws ServiceException
	 */
	void addNewQuestion(int userId, String questionTitle, String questionContent)throws ServiceException;
	
	/**
	 * Get the question by ID
	 * @param questionId
	 * @return {@link Question} by ID
	 * @throws ServiceException
	 */
	Question getQuestionById(int questionId)throws ServiceException;
	
	/**
	 * Update Question by ID
	 * @param questionId
	 * @param questionTitleParamet
	 * @param questionContentParamet
	 * @throws ServiceException
	 */
	void updateQuestionById(int questionId, String questionTitleParamet, String questionContentParamet)throws ServiceException;
	
	boolean isExistQuestion(int questionId)throws ServiceException;
}
