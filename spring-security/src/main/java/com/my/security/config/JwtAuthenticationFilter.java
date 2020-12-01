package com.my.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JWTProvider JWTProvider;
	private final UserDetailsService UserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response, 
									FilterChain filterChain)
									throws ServletException, IOException {
		String jwt = getJWTFromRequest(request);
		if (StringUtils.hasText(jwt) && JWTProvider.validateToken(jwt)) {
            String username = JWTProvider.getUsernameFromJwt(jwt);
            UserDetails userDetails = UserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
    		System.out.println("inside filter3");
        }
		filterChain.doFilter(request, response);
		
	}
	
	public String getJWTFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if(StringUtils.hasText(token) && token.startsWith("Bearer "))
		{
			return token.substring(7);
		}
		return token;
	}

}
