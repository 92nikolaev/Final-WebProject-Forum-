package by.epam.training.helper.test.validation;

import static org.junit.Assert.*;

import org.junit.Test;

import by.epam.training.helper.validation.Validation;
import by.epam.training.helper.validation.exception.ValidationException;

public class ValidationId {
	private final int VALID_ID = 5;
	private final int INVALID_ID = -0;

	@Test
	public void test() throws ValidationException {
		assertTrue(Validation.isValidationId(VALID_ID));
	}
	@Test
	public void test1()  {
		assertFalse(Validation.isValidationId(INVALID_ID));
		
	}
	@Test(expected = ValidationException.class)
	public void test2() throws ValidationException {
		Validation.validationId(INVALID_ID);
	}

	@Test
	public void test3() {
		try {
			Validation.validationId(VALID_ID);
		} catch (ValidationException e) {
			fail("Failed validation id");
		}
	}

}
