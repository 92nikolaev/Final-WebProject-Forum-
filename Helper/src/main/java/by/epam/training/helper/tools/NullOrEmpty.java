package by.epam.training.helper.tools;
/**
 * 
 *  @author Nikolaev Ilya
 *	Class for checking parameters for empty or null
 */
public final class NullOrEmpty {
	
	public static boolean isNullOrEmpty(String item){
		if(item == null || item.equals("")||item.equals("null")){
			return true;
		}else{
			return false;
		}
	}

}
