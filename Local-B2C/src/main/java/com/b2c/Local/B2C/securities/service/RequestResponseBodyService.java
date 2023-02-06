package com.b2c.Local.B2C.securities.service;

import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.securities.dao.FilterRequestsRepository;
import com.b2c.Local.B2C.securities.dao.RequestResponseBodyRepository;
import com.b2c.Local.B2C.securities.model.RequestResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.UUID;

@Log4j2 @Component
public class RequestResponseBodyService extends AbstractHttpMessageConverter {

    @Autowired
    RequestResponseBodyRepository requestResponseBodyRepository;

    @Autowired
    FilterRequestsRepository filterRequestsRepository;

    @Autowired
    HttpSession httpSession;

    @Autowired
    private ObjectMapper objectMapper;

    public RequestResponseBodyService() {
        super(MediaType.ALL);
    }

    @Override
    protected boolean supports(Class clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String inputStream = IOUtils.toString(inputMessage.getBody(), Charset.defaultCharset());
        saveRequestBody(inputStream);
        return objectMapper.readValue(decrypt(IOUtils.toInputStream(inputStream, Charset.defaultCharset())), clazz);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        saveResponseBody(o);
        outputMessage.getBody().write(encrypt(objectMapper.writeValueAsBytes(o)));
    }

    private InputStream decrypt(InputStream inputStream) {
        return inputStream;
    }

    private byte[] encrypt(byte[] bytesToEncrypt) {
        //byte[] encoded = Base64.getEncoder().encode(bytesToEncrypt);
        //byte[] result = ByteBuffer.allocate(bytesToEncrypt.length + encoded.length).put(bytesToEncrypt).put(encoded).array();
        return bytesToEncrypt;
    }

    private User getLoggedInUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((User) principal);
        }
        return null;
    }

    private void saveResponseBody(Object o) throws JsonProcessingException {
        if (requestResponseBodyRepository.existsById(String.valueOf(httpSession.getAttribute("REQUEST_RESPONSE_BODY")))) {
            requestResponseBodyRepository.updateById(String.valueOf(httpSession.getAttribute("REQUEST_RESPONSE_BODY")), objectMapper.writeValueAsString(o));
        } else {
            requestResponseBodyRepository.save(new RequestResponseBody(UUID.randomUUID().toString(), null, filterRequestsRepository.findById(String.valueOf(httpSession.getAttribute("FILTER_REQUEST_ID"))).get(), o));
        }
        httpSession.removeAttribute("REQUEST_RESPONSE_BODY");
        httpSession.removeAttribute("FILTER_REQUEST_ID");
    }

    private void saveRequestBody(@NotNull String requestBody) {
        String id = UUID.randomUUID().toString();
        if (Objects.isNull(httpSession.getAttribute("REQUEST_RESPONSE_BODY"))) {
            httpSession.setAttribute("REQUEST_RESPONSE_BODY", id);
        } else {
            log.error("HttpSession Saving Attribute REQUEST_RESPONSE_BODY Failed");
        }
        requestResponseBodyRepository.save(new RequestResponseBody(id, requestBody, filterRequestsRepository.findById(String.valueOf(httpSession.getAttribute("FILTER_REQUEST_ID"))).get(), null));
    }
}
