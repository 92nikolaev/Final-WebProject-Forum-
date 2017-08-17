package by.epam.training.helper.dao;

import java.util.ArrayList;

import by.epam.training.helper.bean.Question;
import by.epam.training.helper.dao.exception.DAOException;

public interface QuestionDAO {

	ArrayList<Question> getQuestionsWithLimit(int offset, int itemOnPage) throws DAOException;

	int getAmountQuestions()throws DAOException;

	ArrayList<Question> getSearchQuestionWithLimit(int offset, int itemOnPage, String searchQuestion)throws DAOException;

	int getAmountSearchQuestion(String searchQuestion) throws DAOException;

	ArrayList<Question> getUserQuestion(int user_id)throws DAOException;

	void addQuestion(int user_id, String title, String content)throws DAOException;

	Question getQestionById(int questionId)throws DAOException;

	void updateQuestionById(int questionId, String questionTitle, String questionContent)throws DAOException;


}
