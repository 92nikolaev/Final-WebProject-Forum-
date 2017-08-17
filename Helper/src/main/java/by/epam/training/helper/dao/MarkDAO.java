package by.epam.training.helper.dao;

import by.epam.training.helper.bean.Mark;
import by.epam.training.helper.dao.exception.DAOException;

public interface MarkDAO {

	Mark getMarkByUserIdAnswerId(int id, int answerId) throws DAOException;

	void addMark(int id, int answerId, int newMark)throws DAOException;

	void updateMark(int markId, int newMark)throws DAOException;


}
