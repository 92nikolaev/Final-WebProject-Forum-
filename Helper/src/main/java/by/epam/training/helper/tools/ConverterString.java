package by.epam.training.helper.tools;
/**
 * @author Nikolaev Ilya
 *
 */
public class ConverterString {
	
	public static String conversionForSearchDB(String text){
		String search = text.replace("!", "!!")
			    .replace("%", "!%")
			    .replace("_", "!_")
			    .replace("[", "![");
		return "%" + search + "%";
	}
	
	
}