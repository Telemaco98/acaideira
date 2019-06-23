package com.ufrn.imd.acaideira.presentation;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {
	@Inject
	private LoginControllator loginControllator;
	@Inject
	private LoginRestController loginRestControllator;

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getRequestURL().toString();
		System.out.println("URL: " + url);

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		if (loginControllator.getClient() == null && loginRestControllator.getRestaurant() == null)
			response.sendRedirect(request.getServletContext().getContextPath() + "/clientlogin.xhtml");
		else
			chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}
}