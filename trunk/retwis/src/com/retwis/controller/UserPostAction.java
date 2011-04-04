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
 * @author yongboy
 * @date 2011-4-4
 * @version 1.0
 */
@WebServlet("/posts")
public class UserPostAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IStatusService statusService = new StatusServiceImpl();
	private IUserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("name");
		User targetUser = userService.loadByName(userName);

		request.setAttribute("targetUser", targetUser);
		request.setAttribute("posts", statusService.posts(targetUser.getId(), 1));
		request.setAttribute("followers", userService.getFollowers(targetUser.getId()));
		request.setAttribute("followees", userService.getFollowees(targetUser.getId()));
		
		User currentUser = (User) request.getSession().getAttribute("user");
		if (!targetUser.equals(currentUser)) {
			request.setAttribute(
					"following",
					userService.checkFlollowing(currentUser.getId(),
							targetUser.getId()));
		}

		request.getRequestDispatcher("/WEB-INF/html/profile.html").forward(
				request, response);
	}
}