package by.epam.training.helper.service;

import java.util.ArrayList;

import by.epam.training.helper.bean.Question;
import by.epam.training.helper.service.exception.ServiceException;
import by.epam.training.helper.tools.ItemManager;

public interface QuestionService {

	ItemManager<Question> getQuestionsPage(int pageNumber) throws ServiceException;

	ItemManager<Question> getSearchPage(String searchQuestion, int pageNumber)throws ServiceException;

	ArrayList<Question> getUserQuestion(int id) throws ServiceException;

	void addNewQuestion(int id, String questionTitle, String questionContent)throws ServiceException;

	Question getQuestionById(int questionId)throws ServiceException;

	boolean isExistQuestion(int questionId)throws ServiceException;

	void updateQuestionById(int questionId, String questionTitleParamet, String questionContentParamet)throws ServiceException;


}
