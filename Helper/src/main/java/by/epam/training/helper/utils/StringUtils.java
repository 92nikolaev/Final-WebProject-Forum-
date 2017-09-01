package by.epam.training.helper.utils;
/**
 * 
 *  @author Nikolaev Ilya
 *	Class for checking parameters for empty or null
 */
public final class StringUtils {
	public static final String EMPTY = "";
	
	public static boolean isNullOrEmpty(String item){
		return (item == null || item.equals(EMPTY));
	}

}
