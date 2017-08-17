package by.epam.training.helper.constant;
/**
 * @author Nikolaev Ilya
 *
 */
public class SQLCommand {
	
	public static final String INSERT_SIGN_UP = "INSERT INTO user (user_name, user_surname, user_login, user_email, user_password, user_created) VALUES (?,?,?,?,?,CURDATE())";
	public static final String INSERT_QUESTION = "INSERT INTO question (question.user_id, question.question_title, question.question_content, question.question_created) VALUES (?, ?, ?, NOW())";
	public static final String INSERT_ANSWER = "INSERT INTO answer (answer.user_id, answer.question_id,answer.answer_content, answer.answer_created) VALUES (?,?,?, NOW())";
	public static final String INSERT_MARK = "INSERT INTO mark (user_id, answer_id, mark_value) VALUES(?,?,?)";
	public static final String UPDATE_USER_PROFILE = "UPDATE user SET user_name = ?, user_surname = ?, user_email = ? WHERE user_id = ?";
	public static final String UPDATE_USER_PASSWORD= "UPDATE user SET user_password = ? WHERE user_id = ?";
	public static final String UPDATE_USER_STATUS = "UPDATE user SET user.user_status = ? WHERE user.user_id = ?";
	public static final String UPDATE_ANSWER = "UPDATE answer SET answer.answer_content = ? WHERE answer.answer_id = ?";
	public static final String UPDATE_QUESTION = "UPDATE question SET question.question_title = ?, question.question_content = ? WHERE question.question_id = ?";
	public static final String UPDATE_MARK = "UPDATE mark SET mark.mark_value = ? WHERE mark.mark_id = ?";
	public static final String SELECT_SIGN_IN = "SELECT * FROM user WHERE user_login = ? AND user_password = ?";
	public static final String SELECT_USER_BY_ID = "SELECT user_id, user_name, user_surname, user_login, user_email, user_created FROM user WHERE user_id = ?";
	public static final String SELECT_TEN_LAST_QUESTION_USER = "SELECT * FROM question WHERE user_id = ? ORDER BY question_created DESC;";
	public static final String SELECT_QUESTIONS_WITH_LIMIT = "SELECT question.question_id, question.question_title, question.question_content, user.user_login, COUNT(answer.question_id) as count_answer FROM question JOIN user ON question.user_id = user.user_id LEFT JOIN answer ON question.question_id = answer.question_id GROUP BY question.question_id ORDER BY question.question_id DESC LIMIT ?,?;";
	public static final String SELECT_NEWS_HOME_PAGE = "SELECT news_id, news_title FROM news ORDER BY news_title DESC LIMIT ?,?";
	public static final String SELECT_QUESTION_AMOUNT = "SELECT COUNT(*) AS amount_question FROM question";
	public static final String SELECT_USERS_WITH_LIMIT = "SELECT user.user_id, user.user_login, user.user_role, user.user_created, user.user_status, (SELECT AVG(mark.mark_value) FROM mark INNER JOIN answer ON mark.answer_id = answer.answer_id WHERE answer.user_id = user.user_id) AS average_mark, (SELECT COUNT(mark.mark_value) FROM mark INNER JOIN answer ON mark.answer_id = answer.answer_id WHERE answer.user_id = user.user_id) AS count_mark, (SELECT COUNT(*) FROM answer WHERE user.user_id = answer.user_id) as count_answer  FROM user ORDER BY user.user_login LIMIT  ?,?";
	public static final String SELECT_USERS_AMOUNT = "SELECT COUNT(*) AS count_users FROM user";
	public static final String SELECT_QUESTION_BY_ID = "SELECT question.question_id, question.question_title,question.question_content, question.question_created, (COUNT(answer.question_id)) AS count_answer, user.user_login FROM question JOIN user ON user.user_id = question.user_id LEFT JOIN answer ON question.question_id = answer.question_id  where question.question_id = ?";
	public static final String SELECT_ANSWER_WITH_LIMIT_BY_QESTION_ID = "SELECT answer.answer_id, answer.answer_content, answer.answer_created, user.user_login,(SELECT AVG(mark.mark_value) FROM mark WHERE mark.answer_id = answer.answer_id) AS average_mark FROM answer JOIN user ON user.user_id = answer.user_id WHERE  answer.question_id = ? ORDER BY answer.answer_created DESC LIMIT ?,?";
	public static final String SELECT_ANSWER_AMOUNT_BY_QUESTION_ID = "SELECT COUNT(*) AS count_answer FROM answer WHERE answer.question_id = ? ";
	public static final String SELECT_MARK_BY_USER_ID_ANSWER_ID = "SELECT * FROM mark WHERE mark.user_id = ? AND mark.answer_id = ?";
	public static final String SELECT_CHECK_QUESTION = "select checkQuestionExist(?)";
	public static final String SELECT_CHECK_USER_LOGIN_EMAIL= "select checkUserLoginEmail(?, ?)";
	public static final String SELECT_QUESTIONS_SEARCH_WITH_LIMIT = "SELECT question.question_id, question.question_title, question.question_content, user.user_login, COUNT(answer.question_id) as count_answer FROM question JOIN user ON question.user_id = user.user_id LEFT JOIN answer ON question.question_id = answer.question_id WHERE ((LOWER(question.question_content) LIKE LOWER(?)) OR (LOWER(question.question_title) LIKE LOWER(?))) GROUP BY question.question_id ORDER BY question.question_id DESC LIMIT ?,?";
	public static final String SELECT_QUESTIONS_COUNT_SEARCH = "SELECT COUNT(*) AS count_search_qestion FROM question WHERE ((LOWER(question.question_content) LIKE LOWER(?)) OR (LOWER(question.question_title) LIKE LOWER(?)))";
	public static final String SELECT_ANSWER_BY_ID = "SELECT * FROM answer WHERE answer.answer_id = ?";
}