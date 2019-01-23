package com.common.sample.token.filter;

import com.common.sample.token.constant.LoggerParam;
import com.common.sample.token.security.Secured;
import com.common.sample.token.security.SecurityContextHolder;
import com.common.sample.token.security.Token;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

@Component
public class AuthenticationFilter implements HandlerInterceptor
{
    private static final Logger logger = LogManager.getLogger();

    private SecurityContextHolder securityContextHolder;

    @Autowired
    public AuthenticationFilter(SecurityContextHolder securityContextHolder)
    {
        this.securityContextHolder = securityContextHolder;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        try
        {
            if (handler instanceof HandlerMethod)
            {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Secured secured = handlerMethod.getMethod().getAnnotation(Secured.class);

                if (secured == null)
                {
                    //if @Secured annotation is not used, then continue to the next proccess
                    return true;
                }
                else
                {
                    //@Secured annotation is defined, then request must be validate the accessToken from HTTP Request Header
                    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

                    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
                    {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header token key is not set, please check the request already valid and authorized");

                        return false;
                    }
                    else
                    {
                        String token = authorizationHeader.substring("Bearer".length()).trim();

                        //validate the token 
                        validateToken(token);
                        ThreadContext.put(LoggerParam.USER_ID, securityContextHolder.getSubject());
                        logger.info("authenticate, userId:{}", securityContextHolder.getSubject());

                        return true;
                    }
                }
            }
            else
            {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "HTTP Request is not found or invalid url ");
                return false;
            }
        }
        catch (InvalidJwtException mje)
        {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization token from the coming request !");

            return false;
        }
        catch (Exception e)
        {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid request security information, please re-login or check your request ");

            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
    {
        //DO-NOTHING !
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception excptn)
    {
        //DO-NOTHING !
    }

    private void validateToken(String token) throws Exception
    {
        String jwkJson = "{\"kty\":\"oct\",\"k\":\"" + Token.MOBILE_TOKEN + "\"}";
        JsonWebKey jwKey = JsonWebKey.Factory.newJwk(jwkJson);

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setRequireSubject()
                .setExpectedIssuer(Token.MOBILE_ISSUER)
                .setDecryptionKey(jwKey.getKey())
                .setVerificationKey(jwKey.getKey()).build();

        setSecurityContextHolderInformation(jwtConsumer, token);
    }

    private void setSecurityContextHolderInformation(JwtConsumer jwtConsumer, String token) throws Exception
    {
        JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
        securityContextHolder.setAccessToken(token);
        securityContextHolder.setSubject(jwtClaims.getSubject());
        securityContextHolder.setCredential(jwtClaims.getClaimValue(Token.SECURITY_OBJECT));
        securityContextHolder.setExpiration(new Date(jwtClaims.getIssuedAt().getValueInMillis()));
        securityContextHolder.setIssuer(jwtClaims.getIssuer());
    }
}