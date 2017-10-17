$(document).ready(function(){
	$("home").on("swipeleft", function (event) {
			if(event.handled !== true) // This will prevent event triggering more then once
		    {    
			var nextpage = $.mobile.activePage.next('[data-role="page"]');
			// swipe using id of next page if exists
			if (nextpage.length > 0) {
			    $.mobile.changePage(nextpage, {transition: "slide", reverse: false}, true, true);
			}
			event.handled = true;
		    }
		    return false;  
	});
	$("p2").on("swiperight", function (event) {
			if(event.handled !== true) // This will prevent event triggering more then once
		    {    
			var nextpage = $.mobile.activePage.prev('[data-role="page"]');
			// swipe using id of next page if exists
			if (prevpage.length > 0) {
			    $.mobile.changePage(nextpage, {transition: "slide", reverse: false}, true, true);
			}
			event.handled = true;
		    }
		    return false;  
	});
	
});


