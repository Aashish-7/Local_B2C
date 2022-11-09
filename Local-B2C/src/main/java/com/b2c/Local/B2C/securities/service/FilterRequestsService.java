package com.b2c.Local.B2C.securities.service;

import com.b2c.Local.B2C.securities.dao.FilterRequestsRepository;
import com.b2c.Local.B2C.securities.model.FilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.*;
import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class FilterRequestsService extends GenericFilter {

    @Autowired(required = false)
    FilterRequestsRepository filterRequestsRepository;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setRemoteIp(servletRequest.getRemoteAddr());
        filterRequest.setRemotePort(servletRequest.getRemotePort());
        filterRequest.setProtocol(servletRequest.getProtocol());
        filterRequest.setContentType(servletRequest.getContentType());
        filterRequest.setLocalDateTime(LocalDateTime.now());
        filterRequestsRepository.save(filterRequest);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
