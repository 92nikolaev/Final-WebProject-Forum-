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
import by.epam.training.helper.tools.ConverterString;
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
			amountQuestions = questionDAO.getAmountQuestions();
			int amountPage = Calculation.pageCounting(amountQuestions, itemOnPage);
			item = new ItemManager<Question>(questions, amountPage);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
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
			String query = ConverterString.conversionForSearchDB(searchQuestion);
			questions = questionDAO.getSearchQuestionWithLimit(offset, itemOnPage, query);
			countQuestions = questionDAO.getAmountSearchQuestion(searchQuestion);
			int amountPage = Calculation.pageCounting(countQuestions, itemOnPage);
			item = new ItemManager<>(questions, amountPage);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
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
		} catch (DAOException e) {
			logger.error(ErrorMessage.ERROR_QUESTIONS_DB , e);
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
			e.printStackTrace();
			logger.error(e);
			throw new ServiceException(e);
		}	
	}

	@Override
	public Question getQuestionById(int questionId) throws ServiceException {
		Question question = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		QuestionDAO questionDAO = daoFactory.getQuestionDAO();
		
		try {
			question = questionDAO.getQestionById(questionId);
		} catch (DAOException e) {
			logger.error(e);
			e.printStackTrace();
			throw new ServiceException();
		}
		return question;
	}

	@Override
	public boolean isExistQuestion(int questionId) throws ServiceException {
		if(Validation.isValidationId(questionId)){
			try {
				System.out.println("validation  is exits question  " + Validation.isExistQuestion(questionId));
				return	Validation.isExistQuestion(questionId);
			} catch (ValidationException e) {
				logger.error(e);
				e.printStackTrace();
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
}
