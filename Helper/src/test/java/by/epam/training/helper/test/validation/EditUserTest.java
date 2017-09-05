package by.epam.training.helper.test.validation;
import org.junit.Test;

import by.epam.training.helper.validation.Validation;
import by.epam.training.helper.validation.exception.ValidationException;

public class EditUserTest {

	@Test(expected = ValidationException.class)
	public void test() throws ValidationException {
		Validation.validateUserField("Илья", ",s12", "nikolaev@mail.ru");
	}
	@Test(expected = ValidationException.class)
	public void test2() throws ValidationException {
		Validation.validateUserField("Илья", "nikolaev", "nik");
	}
	@Test(expected = ValidationException.class)
	public void test3() throws ValidationException {
		Validation.validateUserField(".,s12", "nikolaev", "nikolaev@mail.ru");
	}

}
