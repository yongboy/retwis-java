package com.retwis.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.retwis.service.IUserService;
import com.retwis.service.UserServiceImpl;

/**
 * 
 * @author yongboy
 * @date 2011-4-4
 * @version 1.0
 */
@WebServlet(urlPatterns = "/init", loadOnStartup = 1)
public class InitAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		config.getServletContext().setAttribute("base",
				config.getServletContext().getContextPath());

		IUserService userService = new UserServiceImpl();
		userService.init();
	}
}