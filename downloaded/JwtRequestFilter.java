package se.lnu.ems.backend.configuration.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import se.lnu.ems.backend.services.auth.AuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Jwt request filter.
 */
/*
 * This code is inspired by
 * https://www.javainuse.com/spring/boot-jwt
 * */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final AuthService authService;

    private final JwtTokenUtil jwtTokenUtil;

    /**
     * Instantiates a new Jwt request filter.
     *
     * @param jwtTokenUtil the jwt token util
     * @param authService  the auth service
     */
    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil, AuthService authService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // allow token to be in Cookie or Authorization header
        String token = extractFromAuthorization(request.getHeader("Authorization"));
        if (token.isEmpty()) {
            token = extractFromCookie(request.getHeader("Cookie"));
        }
        // token is found
        String username = null;
        if (!token.isEmpty()) {
            try {
                username = jwtTokenUtil.getUsernameFromToken(token);
            } catch (IllegalArgumentException | ExpiredJwtException | SignatureException | MalformedJwtException e) {
                logger.warn("Invalid token provided");
            }
        } else {
            logger.warn("JWT Token is not found");
        }

        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.authService.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    private String extractFromAuthorization(String str) {
        if (str != null && str.startsWith("Bearer ")) {
            return str.substring(7);
        }
        return "";
    }

    private String extractFromCookie(String str) {
        String token = "";
        if (str != null) {
            int pos = str.indexOf("token=");
            if (pos >= 0) {
                token = str.substring(pos + 6);
                pos = token.indexOf(';');
                if (pos >= 0) {
                    token = token.substring(0, pos);
                }
            }
        }
        return token;
    }

}
