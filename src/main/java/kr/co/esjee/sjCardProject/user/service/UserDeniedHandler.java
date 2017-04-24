package kr.co.esjee.sjCardProject.user.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class UserDeniedHandler implements AccessDeniedHandler{
	private static final Logger logger = LoggerFactory.getLogger(UserDeniedHandler.class);
	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException accessExcp)
			throws IOException, ServletException {
		
		
		logger.info("Exceiption : {}",accessExcp);
		logger.info("LocalizedMessage : {}",accessExcp.getLocalizedMessage());
		logger.info("Message : {}",accessExcp.getMessage());
		logger.info("StackTrace : {}",accessExcp.getStackTrace());
		
		
		req.setAttribute("errMsg",accessExcp.getMessage());
		req.getRequestDispatcher("/WEB-INF/views/user/denied.jsp").forward(req, res);
		
	}
	
}
