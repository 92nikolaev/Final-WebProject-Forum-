package by.epam.training.helper.dao;

import java.util.ArrayList;

import by.epam.training.helper.bean.Answer;
import by.epam.training.helper.dao.exception.DAOException;

public interface AnswerDAO {

	ArrayList<Answer> getAnswerWithLimit(int questionId, int offset, int itemOnPage) throws DAOException;

	int getAmountAnswers(int questionId)throws DAOException;

	void addAnswer(Answer answer)throws DAOException;

	Answer answerById(int answerId)throws DAOException;

	void updateAnswerById(int answerId, String answerContent)throws DAOException;

}
