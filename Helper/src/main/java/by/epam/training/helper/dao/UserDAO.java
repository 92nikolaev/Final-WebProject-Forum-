package by.epam.training.helper.dao;

import java.util.ArrayList;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.dao.exception.DAOException;

public interface UserDAO {
	/**
	 * Method add new user into database
	 * @param user {@link User}
	 * @throws DAOException
	 */
	void signUp(User user) throws DAOException;
	/**
	 * Method selects user from the database by login and password
	 * @param login 
	 * @param hahsCode hahsCode - encrypted password
	 * @return {@link User}
	 * @throws DAOException
	 */
	User signIn(String login, String hahsCode) throws DAOException;
	/**
	 * Method selects list of users with limit and return list user
	 * @param offset offset of the first question in selected list (0 means from the beginning)
	 * @param itemOnPage itemOnPage - size of questions per page
	 * @return list {@link User}
	 * @throws DAOException
	 */
	ArrayList<User> getUsersWithLimit(int offset, int itemOnPage) throws DAOException;
	/**
	 * Method select amount of users in database 
	 * @return amount of users
	 * @throws DAOException
	 */
	int getCountUser() throws DAOException;
	/**
	 * Method select user by user ID 
	 * @param userId
	 * @return {@link User}
	 * @throws DAOException
	 */
	User getUserById(int userId) throws DAOException;
	/**
	 * Method select password user by user ID
	 * @param userId
	 * @return hashCode hahsCode - encrypted password
	 * @throws DAOException
	 */
	String getPasswordById(int userId)throws DAOException;
	/**
	 * Method update password user by user ID
	 * @param userId
	 * @param newPassword
	 * @throws DAOException
	 */
	void updatePassword(int userId, String newPassword)throws DAOException;
	/**
	 * Method update user field by user ID
	 * @param userId
	 * @param nameEdit
	 * @param surnameEdit
	 * @param emailEdit
	 * @throws DAOException
	 */
	void editUserField(int userId, String nameEdit, String surnameEdit, String emailEdit)throws DAOException;
	/**
	 * Method locks the user if it unlocks of unlocking if it is locked
	 * @param userId
	 * @param status
	 * @throws DAOException
	 */
	void lockUnlockUser(int userId, byte status)throws DAOException;;

}
