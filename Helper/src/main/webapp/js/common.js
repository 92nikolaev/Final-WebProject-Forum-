		var entrance = document.querySelector(".login");
		var popup = document.querySelector(".modal-content");
		var popOff = popup.querySelector(".modal-content-close");
		var login = popup.querySelector("[name=login]");
		var formLog = popup.querySelector("form");
		var pass = popup.querySelector("[name=password]");
		var overlay = document.querySelector(".modal-overlay");

		$('.nav-toggle').on('click', function(){
			$('#menu').toggleClass('active');
			});
		
	entrance.addEventListener("click", function(event) {
		event.preventDefault();
		popup.classList.add("modal-content-show");
		overlay.classList.add("modal-content-show");
	});

	popOff.addEventListener("click", function(event) {
		event.preventDefault();
		popup.classList.remove("modal-content-show");
		popup.classList.remove("modal-error");
		overlay.classList.remove("modal-content-show");
		
	});

	window.addEventListener("keydown", function(event){
		if(event.keyCode === 27){
			if(popup.classList.contains("modal-content-show")) {
				popup.classList.remove("modal-content-show");
				popup.classList.remove("modal-error");
				overlay.classList.remove("modal-content-show");
			}
		}
	});