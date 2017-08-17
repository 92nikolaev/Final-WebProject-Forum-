	var createAnswer = document.querySelector(".create_answer");
	var popupAnswer = document.querySelector(".modal_create_qestion");
	var popOff = popupAnswer.querySelector(".modal-content-close");
	var overlay = document.querySelector(".modal-overlay");
	
	createAnswer.addEventListener("click", function(event) {
		event.preventDefault();
		popupAnswer.classList.add("modal-content-show");
		overlay.classList.add("modal-content-show");
	});
	popOff.addEventListener("click", function(event) {
		event.preventDefault();
		popupAnswer.classList.remove("modal-content-show");
		popupAnswer.classList.remove("modal-error");
		overlay.classList.remove("modal-content-show");
		
	});

	window.addEventListener("keydown", function(event){
		if(event.keyCode === 27){
			if(popupQestion.classList.contains("modal-content-show")) {
				popupAnswer.classList.remove("modal-content-show");
				popupAnswer.classList.remove("modal-error");
				overlay.classList.remove("modal-content-show");
			}
		}
	});	