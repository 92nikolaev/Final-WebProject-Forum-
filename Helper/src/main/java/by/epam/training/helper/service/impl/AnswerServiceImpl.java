package by.epam.training.helper.service.impl;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.Answer;
import by.epam.training.helper.bean.PageItem;
import by.epam.training.helper.constant.ErrorMessageService;
import by.epam.training.helper.dao.AnswerDAO;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.factory.DAOFactory;
import by.epam.training.helper.service.AnswerService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.utils.PaginationUtils;
import by.epam.training.helper.utils.StringUtils;
import by.epam.training.helper.validation.Validation;

public class AnswerServiceImpl implements AnswerService {
	private static final Logger logger = LogManager.getLogger(AnswerServiceImpl.class);
	@Override
	public PageItem<Answer> getAnswerWithLimit(int pageNumber, int questionId) throws ServiceException {
		PageItem<Answer> item = null;
		ArrayList<Answer> answers = null;
		int amountAnswers = 0;
		DAOFactory daoFactory = DAOFactory.getInstance();
		AnswerDAO answerDAO = daoFactory.getAnswerDAO();
		int itemOnPage = 5;
		int offset = (pageNumber - 1) * itemOnPage;
		try {
			answers = answerDAO.getAnswerWithLimit(questionId, offset, itemOnPage);
			amountAnswers = answerDAO.getAmountAnswers(questionId);
			int amountPage = PaginationUtils.pageCounting(amountAnswers, itemOnPage);
			item = new PageItem<>(answers, amountPage);
		} catch (DAOException e) {
			logger.error(ErrorMessageService.ERROR_GET_DATA, e);
			throw new ServiceException(ErrorMessageService.ERROR_GET_DATA);
		}
		return item;
	}
	@Override
	public void addAnswer(Answer answer) throws ServiceException {
		String contnent = answer.getContent().trim();
		if(!StringUtils.isNullOrEmpty(contnent)){
		answer.setContent(contnent);
		DAOFactory daoFactory = DAOFactory.getInstance();
		AnswerDAO answerDAO = daoFactory.getAnswerDAO();
		try {
			answerDAO.addAnswer(answer);
		} catch (DAOException e) {
			logger.error(ErrorMessageService.ERROR_ADD_ANSWER, e);
			throw new ServiceException(ErrorMessageService.ERROR_ADD_ANSWER);
		}
		}else{
			logger.error(ErrorMessageService.ERROR_ANSWER_IS_EMPTY);
			throw new ServiceException(ErrorMessageService.ERROR_ANSWER_IS_EMPTY);
		}
	}
	@Override
	public Answer getAnswerById(int answerId) throws ServiceException {
		Answer answer;
		if(Validation.isValidationId(answerId)){
			DAOFactory daoFactory = DAOFactory.getInstance();
			AnswerDAO answerDAO = daoFactory.getAnswerDAO();
			try {
				answer = answerDAO.answerById(answerId);
			} catch (DAOException e) {
				logger.error(ErrorMessageService.ERROR_GET_ANSWER_BY_ID + answerId, e);
				throw new ServiceException(ErrorMessageService.ERROR_GET_ANSWER_BY_ID + answerId);
			}
		}else {
			logger.error(ErrorMessageService.INVALID_ID + answerId);
			throw new ServiceException(ErrorMessageService.INVALID_ID + answerId);
		}
		return answer;
	}
	@Override
	public void updateAnswerById(int answerId, String answerContent) throws ServiceException {
		if(Validation.isValidationId(answerId)){
			DAOFactory daoFactory = DAOFactory.getInstance();
			AnswerDAO answerDAO = daoFactory.getAnswerDAO();
			try {
				answerDAO.updateAnswerById(answerId, answerContent);
			} catch (DAOException e) {
				logger.error(ErrorMessageService.ERROR_UPDATE_ANSWER_BY_ID + answerId, e);
				throw new ServiceException(ErrorMessageService.ERROR_UPDATE_ANSWER_BY_ID + answerId);
			}
		}else {
			logger.error(ErrorMessageService.INVALID_ID + answerId);
			throw new ServiceException(ErrorMessageService.INVALID_ID + answerId);
		}
		
	}

}
