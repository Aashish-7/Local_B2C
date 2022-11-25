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

import java.util.List;
import java.util.Set;

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

    public List<FilterRequest> getFilterRequestBySessionId(String sessionId) {
        if (filterRequestsRepository.existsAllBySessionId(sessionId)) {
            return filterRequestsRepository.findAllBySessionId(sessionId);
        } else {
            throw new NotFound404Exception("Not Found");
        }
    }

    public Set<String> getActiveSessionId() {
        if (getLoggedInUserId() != null) {
            return sessions.findByPrincipalName(getLoggedInUserId().getEmail()).keySet();
        } else {
            throw new NotFound404Exception("Not Found");
        }
    }

    public void destroyAllSession() {
        if (getActiveSessionId().size() != 0) {
            getActiveSessionId().forEach(s -> {
                sessions.deleteById(s);
            });
        } else {
            throw new BadRequest400Exception("Not Any Active Session");
        }
    }

    public List<FilterRequest> getRequestsBySessionId(String sessionId, String url) {
        if (filterRequestsRepository.existsAllByUrlAndSessionId(url, sessionId)) {
            return filterRequestsRepository.findAllByUrlAndSessionId(url, sessionId);
        } else {
            throw new NotFound404Exception("Not Found");
        }
    }

    public List<FilterRequestInfo> getAllSessionsByUser() {
        if (getLoggedInUserId() != null)
            return filterRequestsRepository.getAllByUserId(getLoggedInUserId().getId().toString());
        else {
            throw new NotFound404Exception("Not Found");
        }
    }
}


