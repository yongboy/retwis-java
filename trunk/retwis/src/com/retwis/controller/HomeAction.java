package com.retwis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.retwis.User;
import com.retwis.service.IStatusService;
import com.retwis.service.IUserService;
import com.retwis.service.StatusServiceImpl;
import com.retwis.service.UserServiceImpl;

/**
 * 
 * @author y.nie
 * @date 2011-4-3
 * @version 1.0
 */
@WebServlet("/home")
public class HomeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IStatusService statusService = new StatusServiceImpl();
	private IUserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");

		request.setAttribute("posts", statusService.timeline(user.getId(), 1));
		
		request.setAttribute("followees", userService.getFollowees(user.getId()));
		request.setAttribute("followers", userService.getFollowers(user.getId()));
		
		request.getRequestDispatcher("/WEB-INF/html/home.html").forward(request, response);
	}
}