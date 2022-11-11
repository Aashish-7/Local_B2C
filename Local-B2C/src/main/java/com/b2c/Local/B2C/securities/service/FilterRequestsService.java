package com.b2c.Local.B2C.securities.service;

import com.b2c.Local.B2C.securities.dao.FilterRequestsRepository;
import com.b2c.Local.B2C.securities.model.FilterRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
@WebFilter
@Log4j2
public class FilterRequestsService extends GenericFilter {

    FilterRequestsRepository filterRequestsRepository;

    @Autowired
    public FilterRequestsService(FilterRequestsRepository filterRequestsRepository) {
        this.filterRequestsRepository = filterRequestsRepository;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("########## Initiating filter ##########");
    }

    @Override
    public void destroy() {
        log.info("########## Destroying filter ##########");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("-------- ------ ----- filtering ServletRequest ------ ------ -----");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setSessionId(httpServletRequest.getSession().getId());
        filterRequest.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        filterRequest.setUrl(httpServletRequest.getRequestURL().toString());
        filterRequest.setRemoteIp(servletRequest.getRemoteAddr());
        filterRequest.setRemoteHost(servletRequest.getRemoteHost());
        filterRequest.setRemotePort(servletRequest.getRemotePort());
        filterRequest.setProtocol(servletRequest.getProtocol());
        filterRequest.setContentType(servletRequest.getContentType());
        filterRequest.setLocalDateTime(LocalDateTime.now());
        filterRequestsRepository.save(filterRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
