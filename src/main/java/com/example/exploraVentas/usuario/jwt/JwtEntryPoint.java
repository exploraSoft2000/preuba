package com.example.exploraVentas.usuario.jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{

    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	@Override
	public void commence(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, AuthenticationException authException)
			throws IOException, jakarta.servlet.ServletException {
		logger.error("fail en el método commence");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autorizado");
		
	}



}
