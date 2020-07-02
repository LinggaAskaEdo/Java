package id.co.homecredit.mobile.common.secure.filter;

import com.google.gson.Gson;
import id.co.homecredit.mobile.common.secure.model.SecureResponse;
import id.co.homecredit.mobile.common.secure.security.SecuredV2;
import id.co.homecredit.mobile.common.secure.security.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter implements HandlerInterceptor
{
    private static final String AUTHENTICATED_HEADER = "x-authenticated-userid";
    private static final String CONTENT_TYPE = "application/json";
    private static final String CHAR_ENCODING = "UTF-8";

    private final SecurityContextHolder securityContextHolder;

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
                SecuredV2 securedV2 = handlerMethod.getMethod().getAnnotation(SecuredV2.class);

                if (null == securedV2)
                {
                    //if @SecuredV2 annotation is not used, then continue to the next proccess
                    return true;
                }
                else
                {
                    //@SecuredV2 annotation is defined, then request must be validate the accessToken from HTTP Request Header
                    String authorizationHeader = request.getHeader(AUTHENTICATED_HEADER);

                    if (null == authorizationHeader)
                    {
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        response.setContentType(CONTENT_TYPE);
                        response.setCharacterEncoding(CHAR_ENCODING);
                        response.getWriter().write(new Gson().toJson(new SecureResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), "Authorization header token key is not set, please check the request")));

                        return false;
                    }
                    else
                    {
                        SecurityContextHolder token = new Gson().fromJson(authorizationHeader, SecurityContextHolder.class);

                        //set values
                        securityContextHolder.setCuid(token.getCuid());
                        securityContextHolder.setMobId(token.getMobId());
                        securityContextHolder.setUserName(token.getUserName());

                        return true;
                    }
                }
            }
            else
            {
                response.setStatus(HttpStatus.NOT_FOUND.value());
                response.setContentType(CONTENT_TYPE);
                response.setCharacterEncoding(CHAR_ENCODING);
                response.getWriter().write(new Gson().toJson(new SecureResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), "HTTP Request is not found or invalid url")));

                return false;
            }
        }
        catch (Exception e)
        {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding(CHAR_ENCODING);
            response.getWriter().write(new Gson().toJson(new SecureResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), "Invalid request security information, please re-login or check your request")));

            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
    {
        //DO-NOTHING
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception excptn)
    {
        //DO-NOTHING
    }
}
