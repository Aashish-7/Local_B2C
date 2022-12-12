package com.b2c.Local.B2C.securities.service;

import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.exception.BadRequest400Exception;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.securities.dao.FilterRequestsRepository;
import com.b2c.Local.B2C.securities.dto.FilterRequestInfo;
import com.b2c.Local.B2C.securities.model.FilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilteringDataService {

    FilterRequestsRepository filterRequestsRepository;

    FindByIndexNameSessionRepository<? extends Session> sessions;


    @Autowired
    public FilteringDataService(FilterRequestsRepository filterRequestsRepository, FindByIndexNameSessionRepository<? extends Session> sessions) {
        this.filterRequestsRepository = filterRequestsRepository;
        this.sessions = sessions;
    }

    private User getLoggedInUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((User) principal);
        }
        return null;
    }

    public List<FilterRequest> getAllSessionRequests() {
        if (getLoggedInUserId() != null)
            return filterRequestsRepository.findAllByUserId(getLoggedInUserId().getId().toString());
        else {
            throw new NotFound404Exception("Not Found");
        }
    }

    public List<FilterRequest> getBySessionId(String sessionId) {
        if (filterRequestsRepository.existsAllBySessionId(sessionId)) {
            return filterRequestsRepository.findAllBySessionId(sessionId);
        } else {
            throw new NotFound404Exception("Not Found");
        }
    }

    public Set<FilterRequest> getFilterRequestBySessionId(String sessionId) {
        Set<FilterRequest> stringSet = getAllSessionRequests().stream().filter(filterRequest ->
                Base64.getEncoder().encodeToString(filterRequest.getSessionId().getBytes(StandardCharsets.UTF_8)
                ).equals(sessionId)).collect(Collectors.toSet());
        return stringSet;
    }

    private Set<String> getActiveSession() {
        if (getLoggedInUserId() != null) {
            return sessions.findByPrincipalName(getLoggedInUserId().getEmail()).keySet();
        } else {
            throw new NotFound404Exception("Not Found");
        }
    }

    public Set<String> getActiveSessionId() {
        Set<String> stringSet = new HashSet<>();
        getActiveSession().forEach(s -> {
            stringSet.add(Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8)));
        });
        return stringSet;
    }

    public void destroyAllSession() {
        if (getActiveSession().size() != 0) {
            getActiveSession().forEach(s -> {
                sessions.deleteById(s);
            });
        } else {
            throw new BadRequest400Exception("Not Any Active Session");
        }
    }

    public List<FilterRequest> getRequestsByRequestId(String requestId, String url) {
        if (filterRequestsRepository.existsAllByUrlAndRequestId(url, requestId)) {
            return filterRequestsRepository.findAllByUrlAndRequestId(url, requestId);
        } else {
            throw new NotFound404Exception("Not Found");
        }
    }

    public List<FilterRequestInfo> getAllRequestIdByUser() {
        if (getLoggedInUserId() != null)
            return filterRequestsRepository.getAllByUserId(getLoggedInUserId().getId().toString());
        else {
            throw new NotFound404Exception("Not Found");
        }
    }

    public Map<String, List<FilterRequest>> getAllActiveSessionDetails() {
        Map<String, List<FilterRequest>> stringListMap = new HashMap<>();
        getActiveSession().forEach(s -> {
            stringListMap.put(Base64.getEncoder().encodeToString(s.getBytes()), getBySessionId(s));
        });
        return stringListMap;
    }

}



