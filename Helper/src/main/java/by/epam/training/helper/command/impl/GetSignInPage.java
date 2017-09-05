package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.utils.StringUtils;
/**
 * Command to go to the Sign in page
 * @author Nikolaev Ilya
 * {@link Command}  invokes method execute() with the request , response  and return jsp question
 */
public class GetSignInPage implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String errorStatus = request.getParameter(ParameterName.ERROR_STATUS);
		if(!StringUtils.isNullOrEmpty(errorStatus)){
			request.setAttribute(ParameterName.ERROR_STATUS, errorStatus);
		}
		request.getRequestDispatcher(Url.SIGN_IN).forward(request, response);
	}

}
