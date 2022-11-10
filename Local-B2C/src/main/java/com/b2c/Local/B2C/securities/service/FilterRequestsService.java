package com.b2c.Local.B2C.securities.service;

import com.b2c.Local.B2C.securities.dao.FilterRequestsRepository;
import com.b2c.Local.B2C.securities.model.FilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
@WebFilter
@Service
public class FilterRequestsService extends GenericFilter {

    FilterRequestsRepository filterRequestsRepository;

    @Autowired
    public FilterRequestsService(FilterRequestsRepository filterRequestsRepository) {
        this.filterRequestsRepository = filterRequestsRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setSessionId(httpServletRequest.getRequestedSessionId());
        filterRequest.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        filterRequest.setUrl(httpServletRequest.getRequestURL().toString());
        filterRequest.setRemoteIp(servletRequest.getRemoteAddr());
        filterRequest.setRemoteHost(servletRequest.getRemoteHost());
        filterRequest.setRemotePort(servletRequest.getRemotePort());
        filterRequest.setProtocol(servletRequest.getProtocol());
        filterRequest.setContentType(servletRequest.getContentType());
        filterRequest.setLocalDateTime(LocalDateTime.now());
        filterRequestsRepository.save(filterRequest);
    }
}
