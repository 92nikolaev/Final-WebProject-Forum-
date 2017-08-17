package by.epam.training.helper.dao;

import by.epam.training.helper.dao.exception.DAOException;

public interface ValidationDAO {

	String checkUserLoginEmailInDB(String login, String email) throws DAOException;

	String checkeExistQuestion(int questionId)throws DAOException;

}
