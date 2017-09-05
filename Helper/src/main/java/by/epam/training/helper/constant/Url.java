package by.epam.training.helper.constant;

/**
 * @author Nikolaev Ilya
 */
public class Url {

	public static final String ERROR = "error.jsp";
	public static final String PROFILE = "profile.jsp";
	public static final String SIGN_IN = "signIn.jsp";
	public static final String HOME = "Helper.jsp";
	public static final String EDIT_ANSWER = "editAnswer.jsp";
	public static final String EDIT_QUESTION = "editQuestion.jsp";
	public static final String LIST_NEWS = "list_news.jsp";
	public static final String USERS = "users.jsp";
	public static final String SIGN_UP = "signUp.jsp";
	public static final String SUCCESSFUL = "successful.jsp";
	public static final String QUESTION = "question.jsp";
	public static final String NEWS = "news.jsp";
	
	public static final String REDIRECT_SIGN_UP = "controller?command=sign_up_page&error_status=";
	public static final String REDIRECT_SIGN_IN = "controller?command=sign_in_page&error_status=";
	public static final String REDIRECT_HOME_PAGE = "controller?command=home";
	public static final String REDIRECT_USER_PROFILE_WITH_MESSAGE = "controller?command=user_profile&message=";
	public static final String SHOW_ALL_USER_WITH_MESSAGE = "controller?command=showAllUsers&message=";
	public static final String REDIRECT_QUESTION_PAGE = "controller?command=show_question&question_id=";
	public static final String REDIRECT_HOME_PAGE_WITH_MESSAGE = "controller?command=home&message=";
	public static final String REDIRECT_QUESTION_PAGE_WITH_MESSAGE = "controller?command=show_question&question_id=%s&message=%s";
	public static final String REDIRECT_HOME_PAGE_WITH_MESSAGE_FORMATING = "controller?command=home&message=%s";
	public static final String REDIRECT_HOME_PAGE_WITH_MESSAGE_FORRMATING = "controller?command=show_question&qestion_id=%d&message=%s";
	public static final String REDIRECT_EDIT_ANSWER = "controller?command=page_edit_answer&answer_id=";
	public static final String REDIRECT_LIST_NEWS_WITH_MESSAGE = "controller?command=show_all_news&message=";
	public static final String REDIRECT_EDIT_QUESTION_PAGE = "controller?command=page_edit_question&question_id=";
	public static final String REDIRECT_SHOW_ALL_NEWS_SUCCESS_ADD = "controller?command=show_all_news&message=news successfully add";
	public static final String REDIRECT_FAILED_ADD_NEWS = "controller?command=home&message=failed add";
	public static final String REDIRECT_SHOW_ALL_NEWS_NOT_ADD_EMPTY = "controller?command=show_all_news&message=news_empty";
	public static final String REDIRECT_USER_PROFILE = "controller?command=user_profile";
	
	
}
