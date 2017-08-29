package by.epam.training.helper.tools;
/**
 * 
 *  @author Nikolaev Ilya
 *	Class for checking parameters for empty or null
 */
public final class NullOrEmpty {
	
	public static boolean isNullOrEmpty(String item){
		return (item == null || item.equals("")||item.equals("null"));
	}

}
