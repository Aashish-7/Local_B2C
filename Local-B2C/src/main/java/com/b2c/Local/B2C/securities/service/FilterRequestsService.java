package com.b2c.Local.B2C.securities.service;

import com.b2c.Local.B2C.auths.dao.UserRepository;
import com.b2c.Local.B2C.securities.dao.FilterRequestsRepository;
import com.b2c.Local.B2C.securities.model.FilterRequest;
import com.b2c.Local.B2C.utility.UserMacAddress;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;


@WebFilter(filterName = "OneFilter")
@Log4j2
public class FilterRequestsService extends GenericFilter {

    FilterRequestsRepository filterRequestsRepository;

    UserRepository userRepository;

    UserMacAddress userMacAddress;

    @Autowired
    public FilterRequestsService(FilterRequestsRepository filterRequestsRepository, UserRepository userRepository, UserMacAddress userMacAddress) {
        this.filterRequestsRepository = filterRequestsRepository;
        this.userRepository = userRepository;
        this.userMacAddress = userMacAddress;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initiating WebFilter For Local_B2C");
    }

    @Override
    public void destroy() {
        log.info("Destroying WebFilter For Local_B2C");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (!httpServletRequest.getSession().isNew()){
            FilterRequest filterRequest = filterRequestsRepository.findBySessionIdAndUrl(httpServletRequest.getSession().getId(), "http://localhost:8080/user/login");
            if (filterRequest.getRemoteIp().equals(httpServletRequest.getRemoteAddr())){
                saveFilterRequest(httpServletRequest.getSession(), httpServletRequest,false);
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                saveFilterRequest(httpServletRequest.getSession(), httpServletRequest,true);
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }else {
            saveFilterRequest(httpServletRequest.getSession(), httpServletRequest,false);
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    public String getUserIdByEmail(String email) {
        return userRepository.findByEmail(email).getId().toString();
    }


    public void saveFilterRequest(HttpSession httpSession, HttpServletRequest httpServletRequest,boolean sessionHijack) throws IOException {
        FilterRequest filterRequest = new FilterRequest();
        getCount(httpServletRequest);
        if (!httpSession.isNew() && Objects.nonNull(httpServletRequest.getUserPrincipal())) {
            Date last = new Date(httpSession.getLastAccessedTime());
            filterRequest.setLastAccessTime(last);
            filterRequest.setNewSession(false);
            filterRequest.setUserId(getUserIdByEmail(httpServletRequest.getUserPrincipal().getName()));
            filterRequest.setUserName(httpServletRequest.getUserPrincipal().getName());
            log.info("Incoming Request From: " + httpServletRequest.getRemoteAddr() + "  Request URL : [" + httpServletRequest.getMethod() + " " + httpServletRequest.getRequestURL().toString() + "] UserPrincipal : [" + httpServletRequest.getUserPrincipal().getName() + "]");
        } else {
            filterRequest.setNewSession(true);
            Date last = new Date(httpSession.getLastAccessedTime());
            filterRequest.setLastAccessTime(last);
            log.info("Incoming Request From: " + httpServletRequest.getRemoteAddr() + "  Request URL : [" + httpServletRequest.getMethod() + " " + httpServletRequest.getRequestURL().toString() + "] UserPrincipal : [Anonymous]");
        }
        filterRequest.setSessionHijack(sessionHijack);
        filterRequest.setSessionId(httpServletRequest.getSession().getId());
        filterRequest.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        filterRequest.setUrl(httpServletRequest.getRequestURL().toString());
        filterRequest.setRemoteIp(httpServletRequest.getRemoteAddr());
        filterRequest.setRemoteHost(httpServletRequest.getRemoteHost());
        filterRequest.setRemotePort(httpServletRequest.getRemotePort());
        filterRequest.setProtocol(httpServletRequest.getProtocol());
        filterRequest.setContentType(httpServletRequest.getContentType());
        filterRequest.setMacAddress(userMacAddress.arpByRemoteIp(httpServletRequest.getRemoteAddr()));
        filterRequestsRepository.save(filterRequest);
    }

    public void getCount(HttpServletRequest httpServletRequest) {
        LocalDateTime localDateTime = LocalDateTime.now().minus(Duration.of(5, ChronoUnit.MINUTES));
        System.out.println(localDateTime);
        System.out.println(LocalDateTime.now());
//        System.out.println(filterRequestsRepository.countByRemoteIpAndLastAccessTimeBetween(httpServletRequest.getRemoteAddr(), date1, date));
    }
}
