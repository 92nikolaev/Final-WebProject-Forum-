package by.epam.training.helper.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.dao.UserDAO;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.factory.DAOFactory;
import by.epam.training.helper.service.UserService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.tools.Calculation;
import by.epam.training.helper.tools.Encryption;
import by.epam.training.helper.tools.ItemManager;
import by.epam.training.helper.validation.Validation;
import by.epam.training.helper.validation.exception.ValidationException;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	@Override
	public void signUp(User user, String password, String verificationPassword) throws ServiceException {
		try {
			Validation.validateSingUp(user, password, verificationPassword);
			user.setPassword(Encryption.getHahsCode(user.getLogin(), password));
			
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			try {
				userDAO.signUp(user);
			} catch (DAOException e) {
				logger.error(e);
				throw new ServiceException(e);
			}
		} catch (ValidationException e) {
			logger.error(e);
			throw new ServiceException(e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
		
	}
	@Override
	public User signIn(String login, String password) throws ServiceException {
		User user = null;
		try {
			Validation.validateSignIn(login, password);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			try {
				user = userDAO.signIn(login, Encryption.getHahsCode(login, password));
				if(user == null){
					logger.error(ErrorMessage.ERROR_NOT_EXISTS_USER);
					throw new ServiceException(ErrorMessage.ERROR_NOT_EXISTS_USER);
				}
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException(e);
			}
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return user;
	}
	@Override
	public ItemManager<User> getUsersPage(int pageNumber) throws ServiceException {
		ItemManager<User> itemManager = null;
		ArrayList<User> users = null;
		int countUsers = 0;
		int itemOnPage = 5;
		int offset = (pageNumber - 1) * itemOnPage;
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			users = userDAO.getUsersWithLimit(offset, itemOnPage);
			countUsers = userDAO.getCountUser();
		} catch (DAOException e) {
			logger.error(e);
			e.printStackTrace();
			throw new ServiceException(e);
		}
		int countPage = Calculation.pageCounting(countUsers, itemOnPage);
		itemManager = new ItemManager<>(users, countPage);
		return itemManager;
	}
	@Override
	public User getUserById(int user_id) throws ServiceException {
		User user = null;
		try {
			Validation.validationId(user_id);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			try {
				user = userDAO.getUserById(user_id);
				if(user == null){
					logger.error(ErrorMessage.ERROR_USER_NOT_FOUND);
					throw new  ServiceException(ErrorMessage.ERROR_USER_NOT_FOUND);
				}
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public void updatePassword(int userId, String userLogin, String oldPassword, String newPassword,
			String vereficationPassword) throws ServiceException {
		try {
			Validation.validatePassword(oldPassword);
			existUserWithPassword(userId, userLogin, oldPassword);
			if(Validation.validateNewPassword(newPassword, vereficationPassword)){
				newPassword = Encryption.getHahsCode(userLogin , newPassword);
				DAOFactory daoFactory = DAOFactory.getInstance();
				UserDAO  userDAO = daoFactory.getUserDAO();
				userDAO.updatePassword(userId, newPassword);
			}
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
	}
	private void existUserWithPassword(int userId, String userLogin, String oldPassword) throws NoSuchAlgorithmException, ServiceException, DAOException {
		String key = Encryption.getHahsCode(userLogin, oldPassword);
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		String hashPassword = null;
		hashPassword = userDAO.getPasswordById(userId);
		if(!key.equals(hashPassword)){
			logger.error(ErrorMessage.ERROR_PASSWORD_NOT_EQUALS);
			throw new ServiceException(ErrorMessage.ERROR_PASSWORD_NOT_EQUALS);
		}
		
		
	}
	@Override
	public void editProfile(int userId, String nameEdit, String surnameEdit, String emailEdit) throws ServiceException {
		try {
			Validation.validateUserField(nameEdit, surnameEdit, emailEdit);
			Validation.validationId(userId);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			try {
				userDAO.editUserField(userId, nameEdit, surnameEdit, emailEdit);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException();
			}
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ServiceException();
		}	
	}
	@Override
	public void lockUser(int userId, byte status) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			userDAO.lockUnlockUser(userId, status);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

}
