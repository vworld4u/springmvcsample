package com.vworld4u.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.vworld4u.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Component
public class PreAuthFilter extends GenericFilterBean {
	private static final Logger log = LoggerFactory.getLogger(PreAuthFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		log.info("Path Info = " + request.getPathInfo() + " Real Path = " + request.getRequestURL());
		System.out.println("Path Info = " + request.getPathInfo() + " Real Path = " + request.getRequestURL());

		if (request.getRequestURL().indexOf("/register") > -1 || request.getRequestURL().indexOf("/login") > -1) {
			chain.doFilter(req, res);
			return;
		}
		final String authHeader = request.getHeader("Authorization");
		log.info("AuthHeader = " + authHeader);
		System.out.println("AuthHeader = " + authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Missing or invalid Authorization header.");
        }

        final String token = authHeader.substring(7); // The part after "Bearer "
        try {
            final Claims claims = Jwts.parser().setSigningKey("secretpass")
                .parseClaimsJws(token).getBody();
            if (new Date().before(claims.getExpiration())) {
            	throw new ServletException("Expired Token. Please login again");
            }
            request.setAttribute("claims", claims);
            Map<String, Object> loggedUser = (Map<String, Object>) claims.get("user");
            System.out.println("Claims from Authorization Header = " + claims);
            log.info("Claims from Authorization Header = " + claims);
            log.info("User = " + loggedUser);
            request.setAttribute("user", loggedUser);
        }
        catch (final SignatureException e) {
            throw new ServletException("Invalid token.");
        }

        chain.doFilter(req, res);
	}

}
