package by.epam.training.helper.dao.factory;

import by.epam.training.helper.dao.AnswerDAO;
import by.epam.training.helper.dao.MarkDAO;
import by.epam.training.helper.dao.NewsDAO;
import by.epam.training.helper.dao.QuestionDAO;
import by.epam.training.helper.dao.UserDAO;
import by.epam.training.helper.dao.ValidationDAO;
import by.epam.training.helper.dao.impl.AnswerSQL;
import by.epam.training.helper.dao.impl.MarkSQL;
import by.epam.training.helper.dao.impl.NewsSQL;
import by.epam.training.helper.dao.impl.QuestionSQL;
import by.epam.training.helper.dao.impl.UserSQL;
import by.epam.training.helper.dao.impl.ValidationSQL;

/**
 * @author Nikolaev Ilya
 *
 */
public class DAOFactory {
	private static DAOFactory instance;
	private QuestionDAO questionDAO = new QuestionSQL();
	private ValidationDAO validationDAO = new ValidationSQL();
	private UserDAO userDAO = new UserSQL();
	private AnswerDAO answerDAO  = new AnswerSQL();
	private MarkDAO markDAO = new MarkSQL();
	private NewsDAO newsDAO = new NewsSQL();
	public DAOFactory() {}
	public static DAOFactory getInstance() {
		if(instance == null){
			instance = new DAOFactory();
		}
		return instance;
	}
	public QuestionDAO getQuestionDAO() {
		return questionDAO;
	}
	public ValidationDAO getValidationDAO() {
		return validationDAO;
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public AnswerDAO getAnswerDAO() {
		return answerDAO;
	}
	public MarkDAO getMarkDAO() {
		return markDAO;
	}
	public NewsDAO getNewsDAO() {
		return newsDAO;
	}
	
}
