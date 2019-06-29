package com.sba.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.*;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
        http.logout().logoutUrl("/logout");
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**").permitAll();
        http.authorizeRequests().antMatchers("/**").authenticated();
        http.httpBasic();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception
//    {
//        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler.setTargetUrlParameter("redirectTo");
//        successHandler.setDefaultTargetUrl("/");
//
//        http.authorizeRequests()
//                .antMatchers("/assets/**").permitAll()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated().and()
//                .formLogin().loginPage("/login")
//                .successHandler(successHandler).and()
//                .logout().logoutUrl("/logout").and()
//                .httpBasic().and()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .ignoringAntMatchers(
//                        "/instances",
//                        "/actuator/**"
//                );
//    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception
//    {
//        http.formLogin().and().authorizeRequests().antMatchers(HttpMethod.POST, "/api/applications")
//                .permitAll()//
//                .antMatchers("/mgmt/health").permitAll()//
//                .anyRequest().authenticated()//
//                .and().csrf().ignoringAntMatchers("/api/**", "/mgmt/**")
//                .csrfTokenRepository(csrfTokenRepository()).and()
//                .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
//    }

//    private Filter csrfHeaderFilter()
//    {
//        return new OncePerRequestFilter()
//        {
//            @Override
//            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
//            {
//                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//
//                if (csrf != null)
//                {
//                    Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
//                    String token = csrf.getToken();
//
//                    if (cookie == null || token != null && !token.equals(cookie.getValue()))
//                    {
//                        cookie = new Cookie("XSRF-TOKEN", token);
//                        cookie.setPath("/");
//                        response.addCookie(cookie);
//                    }
//                }
//
//                filterChain.doFilter(request, response);
//            }
//        };
//    }

//    private CsrfTokenRepository csrfTokenRepository()
//    {
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//        repository.setHeaderName("X-XSRF-TOKEN");
//
//        return repository;
//    }
}