package by.epam.training.helper.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.Answer;
import by.epam.training.helper.bean.Mark;
import by.epam.training.helper.bean.User;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.dao.MarkDAO;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.factory.DAOFactory;
import by.epam.training.helper.service.AnswerService;
import by.epam.training.helper.service.MarkService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;
import by.epam.training.helper.validation.Validation;
import by.epam.training.helper.validation.exception.ValidationException;

public class MarkServiceImpl implements MarkService {
	private static final Logger logger = LogManager.getLogger(MarkServiceImpl.class);
	@Override
	public void addMark(User user, int newMark, int answerId) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AnswerService answerService = serviceFactory.getAnswerService();
		Answer answer = answerService.getAnswerById(answerId);
		if(user.getId()!=answer.getUserId()){
			try {
				Validation.isValidMark(newMark);
				DAOFactory daoFactory = DAOFactory.getInstance();
				MarkDAO markDAO = daoFactory.getMarkDAO();
				Mark mark;
				mark = markDAO.getMarkByUserIdAnswerId(user.getId(), answerId);
				if(mark.getId() == 0){
					markDAO.addMark(user.getId(), answerId, newMark);
				}else{
					markDAO.updateMark(mark.getId(), newMark);
				}
			} catch (ValidationException e) {
				logger.error(e);
				e.printStackTrace();
				throw new ServiceException();	
			} catch (DAOException e) {
				logger.error(e);
				e.printStackTrace();
				throw new ServiceException();
			}
		}else{
			logger.warn(ErrorMessage.USER_TRY_EVALUATE_HIMSELF);;
			throw new ServiceException();
		}
		
	}

}
