package by.epam.training.helper.tools;

/**
 * @author Nikolaev Ilya
 *
 */
public class FormatterString {
	private final static int TITLE_LIMIT_LENGTH = 80;
	private final static int CONTENT_LIMIT_LENGTH = 200;
	private final static String THREE_DOTS = "...";
	
	public static String conversionForSearchDB(String text){
		String search = text.replace("!", "!!")
			    .replace("%", "!%")
			    .replace("_", "!_")
			    .replace("[", "![");
		return "%" + search + "%";
	}

	public static String formattingTitle(String fullTitle) {
		String title = fullTitle.length() > TITLE_LIMIT_LENGTH ? fullTitle.substring(0, TITLE_LIMIT_LENGTH) + THREE_DOTS:fullTitle;
		return title;
	}
	public static String formattingContent(String fullContent) {
		String content = fullContent.length() > CONTENT_LIMIT_LENGTH ? fullContent.substring(0, CONTENT_LIMIT_LENGTH) + THREE_DOTS: fullContent;
		return content;
	}

	

}