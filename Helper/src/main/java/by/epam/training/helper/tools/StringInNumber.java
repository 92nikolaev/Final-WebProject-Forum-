package by.epam.training.helper.tools;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StringInNumber {
	private static final Logger logger = LogManager.getLogger(StringInNumber.class);
	
	public static int parseString(String text, int pageNumber){
		try{
			pageNumber = Integer.parseInt(text);
		}catch (NumberFormatException e) {
			logger.error("Unable to determine the number, came to regueste: " + text);
		}
		return pageNumber;
	}

	public static int parseString(String IdParametr)throws NumberFormatException {
		int id = Integer.parseInt(IdParametr);
		return id;
	}

}
