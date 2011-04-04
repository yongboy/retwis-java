<%
	Object obj = session.getAttribute("user");

	String url = "login";
	if(obj != null){
		url = "home";
	}
	
	request.getRequestDispatcher(url).forward(request, response);
%>