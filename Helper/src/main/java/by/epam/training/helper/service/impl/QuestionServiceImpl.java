package by.epam.training.helper.service.impl;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.Question;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.dao.QuestionDAO;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.factory.DAOFactory;
import by.epam.training.helper.service.QuestionService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.tools.Calculation;
import by.epam.training.helper.tools.FormatterString;
import by.epam.training.helper.tools.ItemManager;
import by.epam.training.helper.validation.Validation;
import by.epam.training.helper.validation.exception.ValidationException;

public class QuestionServiceImpl implements QuestionService {
	private static final Logger logger = LogManager.getLogger(QuestionServiceImpl.class);
	@Override
	public ItemManager<Question> getQuestionsPage(int pageNumber) throws ServiceException {
		ItemManager<Question> item = null;
		ArrayList<Question> questions = null;
		int amountQuestions = 0;
		int itemOnPage = 5;
		int offset = (pageNumber - 1) * itemOnPage;
		DAOFactory daoFactory = DAOFactory.getInstance();
		QuestionDAO questionDAO = daoFactory.getQuestionDAO();
		try {
			questions = questionDAO.getQuestionsWithLimit(offset, itemOnPage);
			formattingQuestion(questions);
			amountQuestions = questionDAO.getAmountQuestions();
			int amountPage = Calculation.pageCounting(amountQuestions, itemOnPage);
			item = new ItemManager<Question>(questions, amountPage);
		} catch (DAOException e) {
			logger.error(ErrorMessage.ERROR_GET_QUESTION_PAGE);
			throw new ServiceException(ErrorMessage.ERROR_GET_QUESTION_PAGE);
		}
		return item;
	}

	@Override
	public ItemManager<Question> getSearchPage(String searchQuestion, int pageNumber) throws ServiceException {
		ItemManager<Question> item = null;
		ArrayList<Question> questions = null;
		int countQuestions = 0;
		int itemOnPage = 5;
		int offset = (pageNumber - 1) * itemOnPage;
		DAOFactory daoFactory = DAOFactory.getInstance();
		QuestionDAO questionDAO = daoFactory.getQuestionDAO();
		try {
			String query = FormatterString.conversionForSearchDB(searchQuestion);
			questions = questionDAO.getSearchQuestionWithLimit(offset, itemOnPage, query);
			formattingQuestion(questions);
			countQuestions = questionDAO.getAmountSearchQuestion(searchQuestion);
			int amountPage = Calculation.pageCounting(countQuestions, itemOnPage);
			item = new ItemManager<>(questions, amountPage);
		} catch (DAOException e) {
			logger.error(ErrorMessage.ERROR_GET_SEARCH_QUESTION_PAGE);
			throw new ServiceException(ErrorMessage.ERROR_GET_SEARCH_QUESTION_PAGE);
		}
		return item;
	}

	@Override
	public ArrayList<Question> getUserQuestion(int user_id) throws ServiceException {
		ArrayList<Question> questions = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		QuestionDAO questionDAO = daoFactory.getQuestionDAO();
		try {
			questions = questionDAO.getUserQuestion(user_id);
			formattingQuestionTitle(questions);
		} catch (DAOException e) {
			logger.error(ErrorMessage.ERROR_NOT_FOUND_QUESTIONS);
			throw new  ServiceException(e);
		}
		return questions;
	}
	@Override
	public void addNewQuestion(int user_id, String questionTitle, String questionContent) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		QuestionDAO questionDAO = daoFactory.getQuestionDAO();
		String title = questionTitle.trim();
		String content = questionContent.trim();
		try {
			questionDAO.addQuestion(user_id, title, content);
		} catch (DAOException e) {
			logger.error(ErrorMessage.ERROR_CREATE_QUESTION);
			throw new ServiceException(ErrorMessage.ERROR_CREATE_QUESTION);
		}	
	}

	@Override
	public Question getQuestionById(int questionId) throws ServiceException {
		Question question = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		QuestionDAO questionDAO = daoFactory.getQuestionDAO();
		
		try {
			Validation.isExistQuestion(questionId);
			question = questionDAO.getQestionById(questionId);
		} catch (DAOException e) {
			logger.error(ErrorMessage.ERROR_QUESTION_NOT_FOUND);
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.ERROR_NOT_FOUND);
		} catch (ValidationException e) {
			logger.error(ErrorMessage.ERROR_QUESTION_NOT_EXISTS + questionId);
			throw new ServiceException(ErrorMessage.ERROR_NOT_EXISTS);
		}
		return question;
	}

	@Override
	public boolean isExistQuestion(int questionId) throws ServiceException {
		if(Validation.isValidationId(questionId)){
			try {
				return	Validation.isExistQuestion(questionId);
			} catch (ValidationException e) {
				logger.error(e);
				throw new ServiceException();
			}
		}
		return false;
	}

	@Override
	public void updateQuestionById(int questionId, String questionTitle, String questionContent)
			throws ServiceException {
		if(Validation.isValidationId(questionId)){
			DAOFactory daoFactory = DAOFactory.getInstance();
			QuestionDAO questionDAO = daoFactory.getQuestionDAO();
			try {
				questionDAO.updateQuestionById(questionId,questionTitle,questionContent);
			} catch (DAOException e) {
				logger.error(e);
				e.printStackTrace();
				throw new ServiceException();
			}
			
		}else {
			logger.error(ErrorMessage.INVALID_ID);
			throw new ServiceException(ErrorMessage.INVALID_ID);
		}
		
	}
	/**
	 * Calls the method for formatting the question for a uniform display on the page
	 * @param questions
	 */
	private void formattingQuestion(ArrayList<Question> questions) {
		for (Question question : questions) {
			String title = FormatterString.formattingTitle(question.getTitle());
			question.setTitle(title);
			String content = FormatterString.formattingContent(question.getContent());
			question.setContent(content);
		}	
	}
	/**
	 * Calls the method for formatting the question title for a uniform display on the page
	 * @param questions
	 */
	private void formattingQuestionTitle(ArrayList<Question> questions) {
		for (Question question : questions) {
			String title = FormatterString.formattingTitle(question.getTitle());
			question.setTitle(title);
		}	
	}
}

