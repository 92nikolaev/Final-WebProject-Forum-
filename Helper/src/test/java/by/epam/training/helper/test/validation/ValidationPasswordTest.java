package by.epam.training.helper.test.validation;


import static org.junit.Assert.*;

import org.junit.Test;

import by.epam.training.helper.validation.Validation;
import by.epam.training.helper.validation.exception.ValidationException;

public class ValidationPasswordTest {
	private final String PASSWORD_VALID = "Salamon123";
	private final String PASSWORD_INVALID = "salamon123";
	
	@Test
	public void testValidPassword() throws ValidationException {
		Validation.validatePassword(PASSWORD_VALID);
	}
	@Test(expected = ValidationException.class)
	public void testInvalidPassword() throws ValidationException {
		Validation.validatePassword(PASSWORD_INVALID);
	}
	@Test
	public void testEqualPassword() throws ValidationException {
		assertTrue(Validation.validateNewPassword(PASSWORD_VALID, PASSWORD_VALID));
	}
	@Test(expected = ValidationException.class)
	public void testNotEqualPassword() throws ValidationException {
		assertFalse(Validation.validateNewPassword(PASSWORD_VALID, PASSWORD_INVALID));
	}
	

}
