<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Object obj = session.getAttribute("user");

	if(obj == null){
		response.sendRedirect("login");
		return;
	}
	request.getRequestDispatcher("home").forward(request, response);
%> 