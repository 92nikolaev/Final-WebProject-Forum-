package by.epam.training.helper.service;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.tools.ItemManager;
/**
 * @author Nikolaev Ilya
 *
 */
public interface UserService {
	
	void signUp(User user, String password, String verificationPassword) throws ServiceException;

	User signIn(String login, String password)throws ServiceException;

	ItemManager<User> getUsersPage(int pageNumber)throws ServiceException;

	User getUserById(int id)throws ServiceException;

	void updatePassword(int id, String login, String oldPassword, String newPassword, String vereficationPassword)throws ServiceException;

	void editProfile(int id, String nameEdit, String surnameEdit, String emailEdit)throws ServiceException;

	void lockUser(int userId, byte status) throws ServiceException;

}
