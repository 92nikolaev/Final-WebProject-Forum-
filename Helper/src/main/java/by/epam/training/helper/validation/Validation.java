package by.epam.training.helper.validation;

import java.util.regex.Pattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.RegularExpression;
import by.epam.training.helper.constant.SuccessMessage;
import by.epam.training.helper.dao.ValidationDAO;
import by.epam.training.helper.dao.exception.DAOException;
import by.epam.training.helper.dao.factory.DAOFactory;
import by.epam.training.helper.validation.exception.ValidationException;

public class Validation {
	private static final Logger logger = LogManager.getLogger(Validation.class);
	private static final int MAX_MARK = 5;
	
	public static void validateSingUp(User user, String password, String verificationPassword) throws ValidationException {
		validateUserField(user);
		validatePassword(password, verificationPassword);
		DAOFactory daoFactory = DAOFactory.getInstance();
		ValidationDAO validationDAO = daoFactory.getValidationDAO();
		try {
			String answer = validationDAO.checkUserLoginEmailInDB(user.getLogin(), user.getEmail());
				switch (answer) {
					case ParameterName.NOT_EXISTS:{
						logger.info(SuccessMessage.USER_NOT_EXISTS + user.getLogin());
					}break;
					case ParameterName.EXISTS_LOGIN:{
						logger.error(ErrorMessage.LOGIN_EXISTS + user.getLogin());
						throw new ValidationException(ErrorMessage.LOGIN_EXISTS + user.getLogin());
					}
					case ParameterName.EXISTS_EMAIL:{
						logger.error(ErrorMessage.EMAIL_EXISTS + user.getEmail());
						throw new ValidationException(ErrorMessage.EMAIL_EXISTS + user.getEmail());
					}case ParameterName.EXISTS_LOGIN_AND_EMAIL:{
						logger.error(ErrorMessage.LOGIN_EXISTS + user.getLogin() + ErrorMessage.EMAIL_EXISTS + user.getEmail());
						throw new ValidationException(ErrorMessage.LOGIN_EXISTS + user.getLogin() + ErrorMessage.EMAIL_EXISTS + user.getEmail());
					}
				}	
		} catch (DAOException e) {
			logger.error(ErrorMessage.ERROR_CHECK_USER_EXISTS, e);
			throw new ValidationException(ErrorMessage.ERROR_CHECK_USER_EXISTS + e);
		}	
	}
	public static void validateSignIn(String login, String password) throws ValidationException {
		if(!Pattern.matches(RegularExpression.LOGIN_REGEX, login)){
			logger.error(ErrorMessage.INVALID_LOGIN + login);
			throw new ValidationException(ErrorMessage.INVALID_LOGIN);
		}
		if(!Pattern.matches(RegularExpression.PASSWORD_REGEX, password)){
			logger.error(ErrorMessage.INVALID_PASSWORD);
			throw new ValidationException(ErrorMessage.INVALID_PASSWORD);
		}
	}

	public static void validatePassword(String password, String verificationPassword) throws ValidationException {
		boolean isValidPassword = Pattern.matches(RegularExpression.PASSWORD_REGEX, password);
		boolean isVerificationPassword = Pattern.matches(RegularExpression.PASSWORD_REGEX, verificationPassword);
		if(isValidPassword && isVerificationPassword && password.equals(verificationPassword)){	
		}else{
			logger.error(ErrorMessage.INVALID_PASSWORD);
			throw new ValidationException(ErrorMessage.INVALID_PASSWORD);
		}
	}
	public static void validatePassword(String password) throws ValidationException {
		if(!Pattern.matches(RegularExpression.PASSWORD_REGEX, password)){
			logger.error(ErrorMessage.INVALID_PASSWORD);
			throw new ValidationException(ErrorMessage.INVALID_PASSWORD);
		}	
	}
	private static void validateUserField(User user) throws ValidationException {
		if(!Pattern.matches(RegularExpression.NAME_REGEX, user.getName())){
			logger.error(ErrorMessage.INVALID_NAME + user.getName());
			throw new ValidationException(ErrorMessage.INVALID_NAME);
		}
		if(!Pattern.matches(RegularExpression.NAME_REGEX, user.getSurname())){
			logger.error(ErrorMessage.INVALID_SURNAME + user.getSurname());
			throw new ValidationException(ErrorMessage.INVALID_SURNAME);
		}
		if(!Pattern.matches(RegularExpression.EMAIL_REGEX, user.getEmail())){
			logger.error(ErrorMessage.INVALID_EMAIL + user.getEmail());
			throw new ValidationException(ErrorMessage.INVALID_EMAIL);
		}
		if(!Pattern.matches(RegularExpression.LOGIN_REGEX, user.getLogin())){
			logger.error(ErrorMessage.INVALID_LOGIN + user.getLogin());
			throw new ValidationException(ErrorMessage.INVALID_LOGIN);
		}	
	}
	public static void validateUserField(String nameEdit, String surnameEdit, String emailEdit) throws ValidationException{
		if(!Pattern.matches(RegularExpression.NAME_REGEX, nameEdit)){
			logger.error(ErrorMessage.INVALID_NAME + nameEdit);
			throw new ValidationException(ErrorMessage.INVALID_NAME);
		}
		if(!Pattern.matches(RegularExpression.NAME_REGEX, surnameEdit)){
			logger.error(ErrorMessage.INVALID_SURNAME + surnameEdit);
			throw new ValidationException(ErrorMessage.INVALID_SURNAME);
		}
		if(!Pattern.matches(RegularExpression.EMAIL_REGEX, emailEdit)){
			logger.error(ErrorMessage.INVALID_EMAIL + emailEdit);
			throw new ValidationException(ErrorMessage.INVALID_EMAIL);
		}
		
	}
	public static void validationId(int id) throws ValidationException {
		if(id <= 0){
			logger.error(ErrorMessage.INVALID_ID + id);
			throw new ValidationException(ErrorMessage.INVALID_ID + id);
		}
	}

	public static boolean validateNewPassword(String password, String verificationPassword) throws ValidationException {
		boolean isValidPassword = Pattern.matches(RegularExpression.PASSWORD_REGEX, password);
		boolean isVerificationPassword = Pattern.matches(RegularExpression.PASSWORD_REGEX, verificationPassword);
		boolean isValid = false;
		if(isValidPassword && isVerificationPassword){
			isValid = password.equals(verificationPassword);
		}else{
			logger.error(ErrorMessage.INVALID_PASSWORD);
			throw new ValidationException(ErrorMessage.INVALID_PASSWORD);
		}
		return isValid;		
	}
	public static boolean isExistQuestion(int questionId) throws ValidationException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		ValidationDAO validationDAO = daoFactory.getValidationDAO();
		boolean existQuestion = false;
		String answer;
		try {
			answer = validationDAO.checkeExistQuestion(questionId);
			System.out.println("answer это валидатор " + answer);
			switch (answer) {
				case ParameterName.EXISTS_QUESTION:{
					logger.info(SuccessMessage.QUESTION_EXISTS + questionId);
					existQuestion = true;
				}break;
				case ParameterName.NOT_EXISTS:{
					logger.error(ErrorMessage.ERROR_QUESTION_NOT_EXISTS + questionId);
					throw new ValidationException(ErrorMessage.LOGIN_EXISTS + questionId);
				}
			}
		} catch (DAOException e) {
			logger.error(ErrorMessage.ERROR_QUESTION_NOT_EXISTS, e);
			throw new ValidationException(ErrorMessage.ERROR_CHECK_USER_EXISTS);
		}
		return existQuestion;
	}
	public static boolean isValidationId(int id) {
		return id <= 0 ? false : true;	
	}
	public static void isValidMark(int mark) throws ValidationException {
		if(mark < 0 || mark > MAX_MARK){
			logger.error(ErrorMessage.ERROR_MARK_VALIDATION);
			throw new ValidationException(ErrorMessage.ERROR_MARK_VALIDATION);
		}
		
	}
}
