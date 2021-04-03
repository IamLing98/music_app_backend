package com.music.gatewayservice.util;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

public class TokenFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(TokenFilter.class);

    @Value("${app.auth.tokenSecret}")
    private String tokenSecret;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private String getTokenFromRequest(HttpServletRequest httpServletRequest) {
        String authHeader;
        try {
            authHeader = httpServletRequest.getHeader("Authorization");
            if (StringUtils.hasText(authHeader)) {
                if (authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    return token;
                } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is not valid");
            } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Require token");
        } catch (Exception ex) {
            System.out.println("No value");
        }
        return null;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token = getTokenFromRequest(request);
        if (validateToken(token)) {

        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is not valid");

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        return null;
    }

}
