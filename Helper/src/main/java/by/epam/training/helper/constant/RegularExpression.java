package by.epam.training.helper.constant;

public class RegularExpression {
	public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])\\w{6,15}$";
	public static final String NAME_REGEX = "[�-��-߸�a-zA-Z0-9-]{3,16}$";;
	public static final String EMAIL_REGEX = "^[a-zA-Z_0-9]+@[a-zA-Z_0-9]+\\.[a-zA-Z_0-9]+$";
	public static final String LOGIN_REGEX = "^[A-Za-z0-9_-]{3,16}$";
}