if ( typeof dwr !== 'undefined' )
{
	dwr.engine.setTextHtmlHandler(function() {
		window.alert(/*[[#{sessionExpired}]]*/);
		document.location = './';
	});

	dwr.engine.setPreHook( function() {
		//angular.element("body").scope().loading = true;
	});

	dwr.engine.setPostHook(function() {
		//angular.element("body").scope().loading = false;
	});
}

//user session
window.user = /*[[${#authentication} ? ${#authentication.principal} : null]]*/ null;