package by.epam.training.helper.service;

import by.epam.training.helper.bean.PageItem;
import by.epam.training.helper.bean.User;
import by.epam.training.helper.service.exception.ServiceException;
/**
 * @author Nikolaev Ilya
 *
 */
public interface UserService {
	
	void signUp(User user, String password, String verificationPassword) throws ServiceException;

	User signIn(String login, String password)throws ServiceException;

	PageItem<User> getUsersPage(int pageNumber)throws ServiceException;

	User getUserById(int id)throws ServiceException;

	void updatePassword(int id, String login, String oldPassword, String newPassword, String vereficationPassword)throws ServiceException;

	void editProfile(int id, String nameEdit, String surnameEdit, String emailEdit)throws ServiceException;

	void lockUnlockUser(int userId, byte status) throws ServiceException;

	void assignModeratorOrUser(int userId, byte moderator_role)throws ServiceException;
}
