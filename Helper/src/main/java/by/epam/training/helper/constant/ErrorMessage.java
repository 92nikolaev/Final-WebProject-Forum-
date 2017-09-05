package by.epam.training.helper.constant;

/**
 * @author Nikolaev Ilya
 */
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
	public static final String ERROR_CHECK_USER_EXISTS = "Failed to check if the user exists with the login or e-mail";
	public static final String ERROR_USER_NOT_FOUND = "User not found in database";
	public static final String ERROR_QUESTIONS_DB = "questions not found user question in database ";
	public static final String ERROR_SIGN_UOT = "Unauthorized user attempts to log out";
	public static final String ERROR_CREATE_QUESTION = "The question was not asked, perhaps you did not fill the title or description";
	public static final String ERROR_CHANGE_PASSWORD = "Failed to change password";
	public static final Object ERROR_CREATE_QUESTION_MESSAGE = "profile_create_question_error_message";
	public static final String ERROR_EDIT_USER_FIELD = "faild change";
	public static final String ERROR_QUESTION_NOT_EXISTS = "Question with such id does not exist: id - ";
	public static final String ERROR_DETERMIN_ID = "Can not determine Id";
	public static final String ERROR_LOCK_USER = "Can not block user";
	public static final String ERROR_ACCESS = "The user does not have permission to do this";
	public static final String ERROR_MARK_VALIDATION = "Mark is less than zero or more than the maximum. Mark = ";
	public static final String ERROR_NOT_ADD_NEWS = "The news has not been added";
	public static final String ERROR_ADD_NEWS = "Failed to check and add news";
	public static final String USER_CAN_NOT_CREATE_NEWS = "user can_not create news";
	public static final String USER_NOT_SIGN_IP = "User is not sign in or the session has expired";
	public static final String INVALID_TITLE_OR_CONTET_NEWS = "The title and text can not contain less than 5 characters";
	public static final String ERROR_ENCRYPTION = "Error encryption";
	public static final String ERROR_LOCK = "Error lock";
	public static final String ERROR_USER_INDEFINED = "Error user indefined";
	public static final String ERROR_UNLOCK = "Error unlock";
	public static final String ERROR_LOCK_HIMSELF = "Error lock himself";
	public static final String ERROR_ENTER_CABINET = "Unable to enter your personal cabinet";
	public static final String ERROR_ANSWER_INVALID = "Invalid answer";
	public static final String ERROR_UNABLE_DETERMINE_NUMBER = "Unable to determine the number, came to regueste: " ;
	public static final String ERROR_QUESTION_NOT_FOUND = "question not_found";
	public static final String ERROR_DETERMINETED_QUESTION_ID = "Failed to determine the id of the question";
	public static final String ERROR_QUESTION_ID_EMPTY = "question id empty";
	public static final String ERROR_NOT_FOUND = "failed found";
	public static final String ERROR_NOT_EXISTS = "don't exists";
	public static final String ERROR_HOME_PAGE_NOT_DISPLAY = "Home can not be displayed";
	public static final String ERROR_USER_ASSIGN_MODERATOR_HIMSELF = "Trying to designate himself as a moderator";
	public static final String ERROR_USER_ASSIGN_ROLE = "Failed to set role";
	public static final String ERROR_COMMAND_NOT_FOUND = "The command that came with from the client was not found. command - ";
	public static final String ERROR_USER_ASSIGN_USER_HIMSELF = "Trying to designate himself as a user";
	public static final String ERROR_DETERMINETED_NEWS_ID = "Failed to determine the id of the news. ID = ";
	public static final String ERROR_SHOW_LIST_NEWS = "Can not process request and display news list";
	public static final String ERROR_NOT_EDIT_NEWS = "Failed update news";
	public static final String USER_CAN_NOT_EDIT_NEWS = "User can not edit news";
	
	
}
