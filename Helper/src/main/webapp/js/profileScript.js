		var edit = document.querySelector(".edit");
		var popupEdit = document.querySelector(".modal-content-edit");
		var popOff = popupEdit.querySelector(".modal-content-close");
		var overlay = document.querySelector(".modal-overlay");

		edit.addEventListener("click", function(event) {
			event.preventDefault();
			popupEdit.classList.add("modal-content-show");
			overlay.classList.add("modal-content-show");
		});
		popOff.addEventListener("click", function(event) {
			event.preventDefault();
			popupEdit.classList.remove("modal-content-show");
			popupEdit.classList.remove("modal-error");
			overlay.classList.remove("modal-content-show");
			
		});

		window.addEventListener("keydown", function(event){
			if(event.keyCode === 27){
				if(popupEdit.classList.contains("modal-content-show")) {
					popupEdit.classList.remove("modal-content-show");
					popupEdit.classList.remove("modal-error");
					overlay.classList.remove("modal-content-show");
				}
			}
		});
		
		var editPassword = document.querySelector(".edit_pasword");
		var popupPassword = document.querySelector(".modal_edit_password");
		var popOff = popupPassword.querySelector(".modal-content-close");
		var overlay = document.querySelector(".modal-overlay");

		editPassword.addEventListener("click", function(event) {
			event.preventDefault();
			popupPassword.classList.add("modal-content-show");
			overlay.classList.add("modal-content-show");
		});
		popOff.addEventListener("click", function(event) {
			event.preventDefault();
			popupPassword.classList.remove("modal-content-show");
			popupPassword.classList.remove("modal-error");
			overlay.classList.remove("modal-content-show");
			
		});

		window.addEventListener("keydown", function(event){
			if(event.keyCode === 27){
				if(popupPassword.classList.contains("modal-content-show")) {
					popupPassword.classList.remove("modal-content-show");
					popupPassword.classList.remove("modal-error");
					overlay.classList.remove("modal-content-show");
				}
			}
		});
		
		var askQuestion = document.querySelector(".ask_question");
		var popupQestion = document.querySelector(".modal_create_qestion");
		var popOff = popupQestion.querySelector(".modal-content-close");
		var overlay = document.querySelector(".modal-overlay");

		askQuestion.addEventListener("click", function(event) {
			event.preventDefault();
			popupQestion.classList.add("modal-content-show");
			overlay.classList.add("modal-content-show");
		});
		popOff.addEventListener("click", function(event) {
			event.preventDefault();
			popupQestion.classList.remove("modal-content-show");
			popupQestion.classList.remove("modal-error");
			overlay.classList.remove("modal-content-show");
			
		});

		window.addEventListener("keydown", function(event){
			if(event.keyCode === 27){
				if(popupQestion.classList.contains("modal-content-show")) {
					popupQestion.classList.remove("modal-content-show");
					popupQestion.classList.remove("modal-error");
					overlay.classList.remove("modal-content-show");
				}
			}
		});	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		