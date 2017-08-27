package by.epam.training.helper.dao;

import by.epam.training.helper.dao.exception.DAOException;


public interface ValidationDAO {
	
	/**
	 * Method checks if there is a user with such a login or email in the database
	 * @param login
	 * @param email
	 * @return String   if exists login return "exists login", 
	 * 		if email exists return "exists email",
	 * 		if login and email exists return "exists login and email"
	 * @throws DAOException
	 */
	String checkUserLoginEmailInDB(String login, String email) throws DAOException;
	
	/**
	 * Method checks if there is a question in the database with such ID
	 * @param questionId
	 * @return String "exists question" or "question not exists"
	 * @throws DAOException
	 */
	String checkeExistQuestion(int questionId)throws DAOException;

}
