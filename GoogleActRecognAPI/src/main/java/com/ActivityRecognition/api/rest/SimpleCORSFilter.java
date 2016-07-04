package com.ActivityRecognition.api.rest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * CORS filter configuration class.
 * 
 * @author aceroDocs
 *
 */
@Component
public class SimpleCORSFilter extends OncePerRequestFilter {

	/**
	 * Override implementation of method 'doFilterInternal()'. Here we could
	 * configure CORS settings.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Expose-Headers",
				"Content-Disposition,Content-Length,fileHash,sesion,fileHash,Set-Cookie,token,rol");
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"GET, POST, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers",
				request.getHeader("Access-Control-Request-Headers"));
		if (request.getMethod().equals("OPTIONS")) {
			try {
				response.getWriter().print("OK");
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}
}