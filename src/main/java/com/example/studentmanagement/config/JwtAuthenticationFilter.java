package com.example.studentmanagement.config;

import io.jsonwebtoken.ExpiredJwtException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        logger.info("Header : {}", requestHeader);
        String username = null;
        String token = null;
        if (requestHeader != null) {
            if (requestHeader.startsWith("Bearer")){
                token = requestHeader.substring(7);
                try{
                    username = this.jwtUtil.getUsernameFromToken(token);

                } catch(IllegalArgumentException e) {
                    logger.info("Illegal Argument while fetching the username");

                } catch(ExpiredJwtException e) {
                    logger.info(("Given jwt token is expired"));

                } catch(Exception e) {
                    e.printStackTrace();

                }
            }

        }  else {
            logger.info("Invalid Header Value !! ");
        }

        if (username !=  null ) {

            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userAuthService.loadUserByUsername(username);
                Boolean validateToken = this.jwtUtil.validateToken(token, userDetails);
                if (validateToken) {

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                } else {
                    logger.info("Validate fails !! ");

                }
            }

        }

        filterChain.doFilter(request, response);

    }
}
