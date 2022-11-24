package com.b2c.Local.B2C.securities.service;

import com.b2c.Local.B2C.auths.dao.UserRepository;
import com.b2c.Local.B2C.securities.dao.FilterRequestsRepository;
import com.b2c.Local.B2C.securities.model.FilterRequest;
import com.b2c.Local.B2C.utility.UserMacAddress;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;


@WebFilter
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
        log.info("Incoming Request From - " + servletRequest.getRemoteAddr() + "  For - " + httpServletRequest.getRequestURI());
        FilterRequest filterRequest = new FilterRequest();
        HttpSession httpSession = httpServletRequest.getSession();
        if (!httpSession.isNew() && Objects.nonNull(httpServletRequest.getUserPrincipal())) {
            Date last = new Date(httpSession.getLastAccessedTime());
            filterRequest.setLastAccessTime(last);
            filterRequest.setNewSession(false);
            filterRequest.setUserId(getUserIdByEmail(httpServletRequest.getUserPrincipal().getName()));
            filterRequest.setUserName(httpServletRequest.getUserPrincipal().getName());
        } else {
            filterRequest.setNewSession(true);
            Date last = new Date(httpSession.getLastAccessedTime());
            filterRequest.setLastAccessTime(last);
        }
        filterRequest.setSessionId(httpServletRequest.getSession().getId());
        filterRequest.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        filterRequest.setUrl(httpServletRequest.getRequestURL().toString());
        filterRequest.setRemoteIp(servletRequest.getRemoteAddr());
        filterRequest.setRemoteHost(servletRequest.getRemoteHost());
        filterRequest.setRemotePort(servletRequest.getRemotePort());
        filterRequest.setProtocol(servletRequest.getProtocol());
        filterRequest.setContentType(servletRequest.getContentType());
        filterRequest.setLocalDateTime(LocalDateTime.now());
        filterRequest.setMacAddress(userMacAddress.arpByRemoteIp(httpServletRequest.getRemoteAddr()));
        filterRequestsRepository.save(filterRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public String getUserIdByEmail(String email) {
        return userRepository.findByEmail(email).getId().toString();
    }

}
