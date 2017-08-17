package by.epam.training.helper.service;

import by.epam.training.helper.bean.User;
import by.epam.training.helper.service.exception.ServiceException;

public interface MarkService {

	void addMark(User user, int mark, int answerId) throws ServiceException;

}
