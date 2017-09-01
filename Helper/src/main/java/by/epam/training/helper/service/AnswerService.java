package by.epam.training.helper.service;

import by.epam.training.helper.bean.Answer;
import by.epam.training.helper.bean.PageItem;
import by.epam.training.helper.service.exception.ServiceException;

public interface AnswerService {

	PageItem<Answer> getAnswerWithLimit(int pageNumber, int questionId)throws ServiceException;

	void addAnswer(Answer answer) throws ServiceException;

	Answer getAnswerById(int answerId)throws ServiceException;

	void updateAnswerById(int answerId, String answerContentParamet)throws ServiceException;

}
