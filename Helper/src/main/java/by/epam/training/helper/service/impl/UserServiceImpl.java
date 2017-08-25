package by.epam.training.helper.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.ErrorStatus;
import by.epam.training.helper.constant.ParameterName;
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
			userDAO.signUp(user);
		} catch (DAOException e) {
				logger.error(ErrorMessage.ERROR_SIGN_UP);
				throw new ServiceException(ErrorMessage.ERROR_SIGN_UP);
		} catch (ValidationException e) {
			String error = getErrorStatus(e.getMessage());
			logger.error(error);
			throw new ServiceException(error);
		} catch (NoSuchAlgorithmException e) {
			logger.error(ErrorMessage.ERROR_ENCRYPTION);
			throw new ServiceException(ErrorMessage.ERROR_SIGN_UP);
		}
		
	}
	@Override
	public User signIn(String login, String password) throws ServiceException {
		User user = null;
		try {
			Validation.validateSignIn(login, password);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			user = userDAO.signIn(login, Encryption.getHahsCode(login, password));
			if(user != null){
				if(user.getStatus() == ParameterName.BAN){
					logger.error(ErrorMessage.ERROR_USER_BAN);
					throw new ServiceException(ErrorMessage.ERROR_USER_BAN);
				}
			}else{
				logger.error(ErrorMessage.ERROR_NOT_EXISTS_USER);
				throw new ServiceException(ErrorMessage.ERROR_SIGN_IN);
			}
		} catch (DAOException e) {
			logger.error(ErrorMessage.ERROR_SIGN_IN);
			throw new ServiceException(ErrorMessage.ERROR_SIGN_IN);
		}catch (ValidationException e) {
			e.printStackTrace();
			String error = getErrorStatus(e.getMessage());
			throw new ServiceException(error);
		}catch (NoSuchAlgorithmException e) {
			logger.error(ErrorMessage.ERROR_ENCRYPTION);
			throw new ServiceException(ErrorMessage.ERROR_SIGN_IN);
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
			int countPage = Calculation.pageCounting(countUsers, itemOnPage);
			itemManager = new ItemManager<>(users, countPage);
			return itemManager;
		} catch (DAOException e) {
			logger.error(ErrorMessage.ERROR_GET_USERS);
			e.printStackTrace();
			throw new ServiceException(ErrorMessage.ERROR_GET_USERS);
		}
	}
	@Override
	public User getUserById(int user_id) throws ServiceException {
		User user = null;
		try {
			Validation.validationId(user_id);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			user = userDAO.getUserById(user_id);
			if(user == null){
				logger.error(ErrorStatus.ERROR_USER_NOT_FOUND);
				throw new  ServiceException(ErrorStatus.ERROR_USER_NOT_FOUND);
				}
		} catch (DAOException e) {
				logger.error(ErrorMessage.ERROR_USER_NOT_FOUND);
				throw new  ServiceException(ErrorMessage.ERROR_USER_NOT_FOUND);
		} catch (ValidationException e) {
			throw new ServiceException(ErrorStatus.INVALID_ID);
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
			logger.error(ErrorMessage.ERROR_USER_NOT_FOUND);
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
			userDAO.editUserField(userId, nameEdit, surnameEdit, emailEdit);
		} catch (DAOException e) {
			logger.error(ErrorStatus.ERROR_USER_NOT_FOUND);
				e.printStackTrace();
				throw new ServiceException(ErrorStatus.ERROR_USER_NOT_FOUND);
		} catch (ValidationException e) {
			String error = getErrorStatus(e.getMessage());
			throw new ServiceException(error);
		}	
	}
	@Override
	public void lockUser(int userId, byte status) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			userDAO.lockUnlockUser(userId, status);
		} catch (DAOException e) {
			logger.error(ErrorStatus.USER_NOT_LOCK);
			e.printStackTrace();
			throw new ServiceException(ErrorStatus.USER_NOT_LOCK);
		}
	}
	private String getErrorStatus(String message) {
		String error = null;
		switch (message) {
		case ErrorMessage.LOGIN_EXISTS:
			error = ErrorStatus.LOGIN_EXISTS;
			break;
		case ErrorMessage.EMAIL_EXISTS:
			error = ErrorStatus.EMAIL_EXISTS;
			break;
		case ErrorMessage.LOGIN_EMAIL_EXISTS:
			error = ErrorStatus.LOGIN_EMAIL_EXISTS;
			break;
		case ErrorMessage.INVALID_NAME:
			error = ErrorStatus.INVALID_NAME;
			break;
		case ErrorMessage.INVALID_SURNAME:
			error = ErrorStatus.INVALID_SURNAME;
			break;
		case ErrorMessage.INVALID_LOGIN:
			error = ErrorStatus.INVALID_LOGIN;
			break;
		case ErrorMessage.INVALID_PASSWORD:
			error = ErrorStatus.INVALID_PASSWORD;
			break;
		case ErrorMessage.INVALID_EMAIL:
			error = ErrorStatus.INVALID_EMAIL;
			break;
		case ErrorMessage.INVALID_ID:
			error = ErrorStatus.INVALID_ID;
			break;
		default:
			error = ErrorStatus.ERROR_SIGN_UP;
			break;
		}
		return error;
	}

}
