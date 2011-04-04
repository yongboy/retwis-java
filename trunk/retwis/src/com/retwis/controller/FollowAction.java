package com.retwis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.retwis.service.IUserService;
import com.retwis.service.UserServiceImpl;

@WebServlet({ "/follow", "/unfollow" })
public class FollowAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String currUserName = request.getParameter("curr");
		String targetUserName = request.getParameter("target");

		String servletPath = request.getServletPath();

		if (servletPath.contains("/follow")) {
			userService.addFollowee(currUserName, targetUserName);
		} else {
			userService.removeFollowee(currUserName, targetUserName);
		}

		response.sendRedirect(request.getContextPath() + "/" + targetUserName + "/");
	}
}