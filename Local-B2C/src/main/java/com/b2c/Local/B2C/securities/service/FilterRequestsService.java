package com.b2c.Local.B2C.securities.service;

import com.b2c.Local.B2C.auths.dao.UserRepository;
import com.b2c.Local.B2C.securities.dao.FilterRequestsRepository;
import com.b2c.Local.B2C.securities.model.FilterRequest;
import com.b2c.Local.B2C.utility.UserMacAddress;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;


@WebFilter(filterName = "OneFilter")
@Log4j2
public class FilterRequestsService extends GenericFilter {

    FilterRequestsRepository filterRequestsRepository;

    UserRepository userRepository;

    UserMacAddress userMacAddress;

    FindByIndexNameSessionRepository<? extends Session> sessions;

    @Autowired
    public FilterRequestsService(FilterRequestsRepository filterRequestsRepository, UserRepository userRepository, UserMacAddress userMacAddress, FindByIndexNameSessionRepository<? extends Session> sessions) {
        this.filterRequestsRepository = filterRequestsRepository;
        this.userRepository = userRepository;
        this.userMacAddress = userMacAddress;
        this.sessions = sessions;
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
        saveFilterRequest(httpServletRequest.getSession(), httpServletRequest, false);
        if (getCount(httpServletRequest) < 5) {
            if (validateSessionAndUrl(httpServletRequest.getSession(), httpServletRequest)) {
                log.warn("Session Hijack Different RemoteIp Address Found :" + httpServletRequest.getRemoteAddr());
                saveFilterRequest(httpServletRequest.getSession(), httpServletRequest, true);
                sessions.deleteById(httpServletRequest.getSession().getId());
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }else {
            log.warn("Too Many Request From RemoteIp Address :" + httpServletRequest.getRemoteAddr());
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.sendError(429);
        }
    }

    public String getUserIdByEmail(String email) {
        return userRepository.findByEmail(email).getId().toString();
    }


    public void saveFilterRequest(HttpSession httpSession, HttpServletRequest httpServletRequest, boolean sessionHijack) throws IOException {
        FilterRequest filterRequest = new FilterRequest();
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

    public long getCount(HttpServletRequest httpServletRequest) {
        LocalDateTime localDateTime = LocalDateTime.now().minus(Duration.of(5, ChronoUnit.SECONDS));
        Instant i = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(i);
        LocalDateTime local = LocalDateTime.now();
        Instant b = local.atZone(ZoneId.systemDefault()).toInstant();
        Date dateInstant = Date.from(b);
        return filterRequestsRepository.countByRemoteIpAndLastAccessTimeBetween(httpServletRequest.getRemoteAddr(), date, dateInstant);
    }

    private boolean validateSessionAndUrl(HttpSession httpSession, HttpServletRequest httpServletRequest) {
        return !httpSession.isNew() && !Objects.isNull(httpServletRequest.getUserPrincipal()) && !filterRequestsRepository.findBySessionIdAndUrlIsEndingWith(httpSession.getId(), "8080/user/login").getRemoteIp().equals(httpServletRequest.getRemoteAddr());
    }
}
