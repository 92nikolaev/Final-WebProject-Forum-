package by.epam.training.helper.constant;

public class ErrorMessage {
	
	public static final String INVALID_NAME = "Invalid user name ";
	public static final String INVALID_SURNAME = "Invalid user surname ";
	public static final String INVALID_EMAIL = "Invalid user email ";
	public static final String INVALID_LOGIN = "Invalid user login ";
	public static final String INVALID_PASSWORD = "Invalid user password";
	public static final String LOGIN_EXISTS = " User with such login exists: login - ";
	public static final String EMAIL_EXISTS = " User with such email exists: email - ";
	public static final String LOGIN_EMAIL_EXISTS = "A user with such login email exists ";
	public static final String INVALID_ID = "Invalid id: ";
	public static final String ERROR_CHECK_USER_EXISTS = "Failed to check if the user exists with the username or e-mail ";
	public static final String ERROR_SAVE_USER = "Failed to save user to database ";
	public static final String ERROR_GET_USER = "Failed to get user data from database ";
	public static final String ERROR_GET_USERS = "Failed to get users  from database ";
	public static final String ERROR_NOT_EXISTS_USER = "User not exist or login or passwor incorect ";
	public static final String ERROR_CONNECTION ="An error occurred while connecting to the database ";
	public static final String ERROR_GET_COUNT_USERS = "Failed to get the number of users  from database ";
	public static final String ERROR_USER_NOT_FOUND = "User not found in database";
	public static final String ERROR_SIGN_IN = "Could not sign in ";
	public static final String ERROR_QUESTIONS_DB = "questions not found in database ";
	public static final String ERROR_GET_QUESTIONS = "Failed to get questions data from database";
	public static final String ERROR_GET_SEARCH_QUESTIONS = "Failed to get search questions data from database";
	public static final String ERROR_GET_COUNT_QUESTIONS = "Failed to get number of questions  from database";
	public static final String ERROR_SIGN_UOT = "Unauthorized user attempts to log out";
	public static final String ERROR_SAVE_QUESTION = "Failed to save query in database";
	public static final String ERROR_CREATE_QUESTION = "The question was not asked, perhaps you did not fill the title or description";
	public static final String ERROR_PASSWORD_NOT_EQUALS = "The password in the database does not match what the user entered";
	public static final String ERROR_GET_CURRENT_PASSWORD = "Failed to get the current user passwordd ";
	public static final String ERROR_UPDATE_PASSWORD = "Failed to update user password from database";
	public static final String ERROR_CHANGE_PASSWORD = "Failed to change password";
	public static final String ERROR_EDIT_USER_FIELD = "Could not change user data";
	public static final String ERROR_UPDATE_USER = "Failed to update user  from database";
	public static final String ERROR_GET_ANSWER = "Failed to get answers from database";
	public static final String ERROR_GET_COUNT_ANSWERS = "Failed to get the number of users  from database";
	public static final String ERROR_QUESTION_NOT_EXISTS = "Question with such id does not exist: id - ";
	public static final String ERROR_DETERMIN_ID = "Can not determine Id";
	public static final String ERROR_LOCK_USER = "Can not block user";
	public static final String ERROR_ACCESS = "The user does not have permission to do this";
	public static final String ERROR_UPDATE_ANSWER = "Could not update answer";
	public static final String ERROR_UPDATE_QUESTION = "Could not update question";
	public static final String USER_TRY_EVALUATE_HIMSELF = "The user try to evaluate himself";
	public static final String ERROR_MARK_VALIDATION = "Mark is less than zero or more than the maximum";
	public static final String ERROR_INSER_MARK = "From evaluate the answer";
}
