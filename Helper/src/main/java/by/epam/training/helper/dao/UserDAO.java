package by.epam.training.helper.dao;

import java.util.ArrayList;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.dao.exception.DAOException;

public interface UserDAO {

	void signUp(User user) throws DAOException;

	User signIn(String login, String hahsCode) throws DAOException;

	ArrayList<User> getUsersWithLimit(int offset, int itemOnPage) throws DAOException;

	int getCountUser() throws DAOException;

	User getUserById(int user_id) throws DAOException;

	String getPasswordById(int userId)throws DAOException;

	void updatePassword(int userId, String newPassword)throws DAOException;

	void editUserField(int userId, String nameEdit, String surnameEdit, String emailEdit)throws DAOException;

	void lockUnlockUser(int userId, byte status)throws DAOException;;

}
