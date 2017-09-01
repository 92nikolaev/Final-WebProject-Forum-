package by.epam.training.helper.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.training.helper.constant.ErrorMessage;
import by.epam.training.helper.constant.InfoMessage;

public class StringParser {
	private static final Logger logger = LogManager.getLogger(StringParser.class);
	
	public static int parseString(String parametr, int pageNumber){
		logger.info(InfoMessage.PARAMETR + parametr);
		try{
			return Integer.parseInt(parametr);
		}catch (NumberFormatException e) {
			logger.error(ErrorMessage.ERROR_UNABLE_DETERMINE_NUMBER + parametr);
		}
		return pageNumber;
	}

	public static int parseString(String parametr)throws NumberFormatException {
		logger.info(InfoMessage.PARAMETR + parametr);
		return Integer.parseInt(parametr);
	}


}
