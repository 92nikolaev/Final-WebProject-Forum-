package by.epam.training.helper.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.command.Command;
import by.epam.training.helper.command.exception.CommandException;
import by.epam.training.helper.constant.ParameterName;
import by.epam.training.helper.constant.Url;
import by.epam.training.helper.service.MarkService;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.service.factory.ServiceFactory;

public class EvaluateAnswer implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommandException {
		String markValueParamet = request.getParameter(ParameterName.MARK_VALUE);
		String answerIdParametr = request.getParameter(ParameterName.ANSWER_ID);
		String questionIdParametr = request.getParameter(ParameterName.QUESTION_ID);
		try{
			int mark = Integer.parseInt(markValueParamet);
			int answerId = Integer.parseInt(answerIdParametr);
			int questionId = Integer.parseInt(questionIdParametr);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(ParameterName.USER);
			if(user != null){
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				MarkService markService = serviceFactory.getMarkService();
				markService.addMark(user, mark, answerId);
				response.sendRedirect("controller?command=show_question&question_id="+questionId);
			}else {
				response.sendRedirect(Url.SIGN_IN);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
