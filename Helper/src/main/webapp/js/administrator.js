		var createNews = document.querySelector(".createNews");
		var popupNews = document.querySelector(".modal_create_news");
		var popOff = popupNews.querySelector(".modal-content-close");
		var overlay = document.querySelector(".modal-overlay");

		createNews.addEventListener("click", function(event) {
		event.preventDefault();
		popupNews.classList.add("modal-content-show");
		overlay.classList.add("modal-content-show");
	});

	popOff.addEventListener("click", function(event) {
		event.preventDefault();
		popupNews.classList.remove("modal-content-show");
		popupNews.classList.remove("modal-error");
		overlay.classList.remove("modal-content-show");
		
	});

	window.addEventListener("keydown", function(event){
		if(event.keyCode === 27){
			if(popup.classList.contains("modal-content-show")) {
				popupNews.classList.remove("modal-content-show");
				popupNews.classList.remove("modal-error");
				overlay.classList.remove("modal-content-show");
			}
		}
	});