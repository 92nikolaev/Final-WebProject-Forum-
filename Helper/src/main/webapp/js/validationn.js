const ValidateFormRules = {
			user_name: {rule: 'name', message: " First name must be 5 characters or less."},
			user_surname: {rule: 'name', message: " Last name must be 5 characters or less."},
			user_login: {rule: 'login', message: " This login is not valid..(example: Furious07)"},
			user_email: {rule: 'email', message: " This email address is not available."},
			user_password: {rule: 'password', message: " This security code is not valid.(example: siTo123)."},
			user_confirm_password: {rule: 'equal', message: " The passwords you entered do not match.", equal: 'user_password'},     		
		}
	
		function validate(form) {
		for (field in ValidateFormRules) {
			isFieldValid(form, field)
			? hideValidationError(form, field)
			: showValidationError(form, field)
		}
		}
		
		function isFieldValid(form, field) {
			switch(ValidateFormRules[field].rule) {
				case 'name': 
					return validateName(form.elements[field].value);
				case 'login': 
					return validateLogin(form.elements[field].value);
				case 'string': 
					return !!form.elements[field].value;
				case 'equal': 
					return form.elements[field].value === form.elements[ValidateFormRules[field].equal].value;
				case 'email': 
					return validateEmail(form.elements[field].value);
				case 'password': 
					return validatePassword(form.elements[field].value);
			}
		}
		
		function validateName(name) {
			var reg_name =  /^[A-Za-z][\w]{5,}/;
			return reg_name.test(name);
		}
		
		function validateLogin(name) {
			var reg_login =  /^[A-Za-z0-9_-]{3,16}$/;
			return reg_login.test(name);
		}
		
		function validateEmail(email) {
			var reg_email = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			return reg_email.test(email);
		}	
		
		function validatePassword(str){
			var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$/;
			return re.test(str);
		}
		
		function showValidationError(form, field) {
		let fieldLabel = form.elements[field].parentNode; 
		if (fieldLabel.classList.contains('error')) return
		
		let errorMessage = document.createElement('span')
		errorMessage.innerHTML = ValidateFormRules[field].message
		
		fieldLabel.appendChild(errorMessage);
		fieldLabel.classList.add('error');
		}
		
		function hideValidationError(form, field) {
		let fieldLabel = form.elements[field].parentNode; 
		if (!fieldLabel.classList.contains('error')) return
		
		fieldLabel.classList.remove('error');
		
		let errorMessage = fieldLabel.querySelector('span');
		
		if (!errorMessage) return
		fieldLabel.removeChild(errorMessage)
		}
		
	