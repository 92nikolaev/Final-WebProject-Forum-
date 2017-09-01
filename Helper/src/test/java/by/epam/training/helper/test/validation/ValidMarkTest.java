package by.epam.training.helper.test.validation;

import org.junit.Test;

import by.epam.training.helper.validation.Validation;
import by.epam.training.helper.validation.exception.ValidationException;

public class ValidMarkTest {
	private final int MARK_FIRST = -1;
	private final int MARK_SECOND = 0;
	private final int MARK_THIRD = 6;

	@Test(expected = ValidationException.class)
	public void test() throws ValidationException {
		Validation.isValidMark(MARK_FIRST);;
	}
	@Test(expected = ValidationException.class)
	public void test2() throws ValidationException {
		Validation.isValidMark(MARK_SECOND);;
	}
	@Test(expected = ValidationException.class)
	public void test3() throws ValidationException {
		Validation.isValidMark(MARK_THIRD);;
	}

}
