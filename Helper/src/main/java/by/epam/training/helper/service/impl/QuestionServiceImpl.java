package by.epam.training.helper.service.impl;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.PageItem;
import by.epam.training.helper.bean.Question;
import by.epam.training.helper.constant.ErrorMessageService;
import by.epam.training.helper.dao.QuestionDAO;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.factory.DAOFactory;
import by.epam.training.helper.service.QuestionService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.utils.PaginationUtils;
import by.epam.training.helper.utils.StringConverter;
import by.epam.training.helper.validation.Validation;
import by.epam.training.helper.validation.exception.ValidationException;

public class QuestionServiceImpl implements QuestionService {
	private static final Logger logger = LogManager.getLogger(QuestionServiceImpl.class);
	private final int ITEM_ON_PAGE = 10;
	@Override
	public PageItem<Question> getQuestionsPage(int pageNumber) throws ServiceException {
		PageItem<Question> item = null;
		ArrayList<Question> questions = null;
		int amountQuestions = 0;
		int offset = (pageNumber - 1) * ITEM_ON_PAGE;
		DAOFactory daoFactory = DAOFactory.getInstance();
		QuestionDAO questionDAO = daoFactory.getQuestionDAO();
		try {
			questions = questionDAO.getQuestionsWithLimit(offset, ITEM_ON_PAGE);
			formattingQuestion(questions);
			amountQuestions = questionDAO.getAmountQuestions();
			int amountPage = PaginationUtils.pageCounting(amountQuestions, ITEM_ON_PAGE);
			item = new PageItem<Question>(questions, amountPage);
		} catch (DAOException e) {
			logger.error(ErrorMessageService.ERROR_GET_QUESTION_PAGE);
			throw new ServiceException(ErrorMessageService.ERROR_GET_QUESTION_PAGE);
		}
		return item;
	}

	@Override
	public PageItem<Question> getSearchPage(String searchQuestion, int pageNumber) throws ServiceException {
		PageItem<Question> item = null;
		ArrayList<Question> questions = null;
		int countQuestions = 0;
		int offset = (pageNumber - 1) * ITEM_ON_PAGE;
		DAOFactory daoFactory = DAOFactory.getInstance();
		QuestionDAO questionDAO = daoFactory.getQuestionDAO();
		try {
			String query = StringConverter.conversionForSearchDB(searchQuestion);
			questions = questionDAO.getSearchQuestionWithLimit(offset, ITEM_ON_PAGE, query);
			formattingQuestion(questions);
			countQuestions = questionDAO.getAmountSearchQuestion(searchQuestion);
			int amountPage = PaginationUtils.pageCounting(countQuestions, ITEM_ON_PAGE);
			item = new PageItem<>(questions, amountPage);
		} catch (DAOException e) {
			logger.error(ErrorMessageService.ERROR_GET_SEARCH_QUESTION_PAGE,e);
			throw new ServiceException(ErrorMessageService.ERROR_GET_SEARCH_QUESTION_PAGE);
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
			logger.error(ErrorMessageService.ERROR_NOT_FOUND_QUESTIONS, e);
			throw new  ServiceException(ErrorMessageService.ERROR_NOT_FOUND_QUESTIONS);
		}
		return questions;
	}
	@Override
	public void addNewQuestion(int userId, String questionTitle, String questionContent) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		QuestionDAO questionDAO = daoFactory.getQuestionDAO();
		String title = questionTitle.trim();
		String content = questionContent.trim();
		try {
			questionDAO.addQuestion(userId, title, content);
		} catch (DAOException e) {
			logger.error(ErrorMessageService.ERROR_ADD_QUESTION, e);
			throw new ServiceException(ErrorMessageService.ERROR_ADD_QUESTION);
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
			logger.error(ErrorMessageService.ERROR_QUESTION_NOT_FOUND, e);
			throw new ServiceException(ErrorMessageService.ERROR_QUESTION_NOT_FOUND);
		} catch (ValidationException e) {
			logger.error(ErrorMessageService.ERROR_QUESTION_NOT_EXISTS + questionId);
			throw new ServiceException(ErrorMessageService.ERROR_QUESTION_NOT_EXISTS + questionId);
		}
		return question;
	}

	@Override
	public boolean isExistQuestion(int questionId) throws ServiceException {
		if(Validation.isValidationId(questionId)){
			try {
				return	Validation.isExistQuestion(questionId);
			} catch (ValidationException e) {
				logger.error(ErrorMessageService.ERROR_QUESTION_NOT_EXISTS + questionId);
				throw new ServiceException(ErrorMessageService.ERROR_QUESTION_NOT_EXISTS + questionId);
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
				logger.error(ErrorMessageService.ERROR_QUESTION_NOT_UPDATE + questionId);
				throw new ServiceException(ErrorMessageService.ERROR_QUESTION_NOT_UPDATE);
			}
		}else {
			logger.error(ErrorMessageService.INVALID_ID + questionId);
			throw new ServiceException(ErrorMessageService.INVALID_ID + questionId);
		}
		
	}
	/**
	 * Calls the method for formatting the question for a uniform display on the page
	 * @param questions
	 */
	private void formattingQuestion(ArrayList<Question> questions) {
		for (Question question : questions) {
			String title = StringConverter.formattingTitle(question.getTitle());
			question.setTitle(title);
			String content = StringConverter.formattingContent(question.getContent());
			question.setContent(content);
		}	
	}
	/**
	 * Calls the method for formatting the question title for a uniform display on the page
	 * @param questions
	 */
	private void formattingQuestionTitle(ArrayList<Question> questions) {
		for (Question question : questions) {
			String title = StringConverter.formattingTitle(question.getTitle());
			question.setTitle(title);
		}	
	}
}

