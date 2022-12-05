package com.b2c.Local.B2C.securities.service;

import com.b2c.Local.B2C.auths.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

@ControllerAdvice
@Log4j2
public class JsonDataConverter extends AbstractHttpMessageConverter {

    @Autowired
    private ObjectMapper objectMapper;

    public JsonDataConverter() {
        super(MediaType.ALL);
    }

    @Override
    protected boolean supports(Class clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String readRequest = IOUtils.toString(decrypt(inputMessage.getBody()), Charset.defaultCharset());
        log.info("Accept Request Message : "+readRequest+" From CurrentUserPrincipal :["+((getLoggedInUserId() != null)?getLoggedInUserId().getEmail():"Anonymous")+"]");
        return objectMapper.readValue(decrypt(IOUtils.toInputStream(readRequest,Charset.defaultCharset())), clazz);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody().write(encrypt(objectMapper.writeValueAsBytes(o)));
        log.info("Sending Response Message : "+objectMapper.writeValueAsString(o)+" For CurrentUserPrincipal :["+((getLoggedInUserId() != null)?getLoggedInUserId().getEmail(): "Anonymous")+"]");
    }

    private InputStream decrypt(InputStream inputStream){
        return inputStream;
    }

    private byte[] encrypt(byte[] bytesToEncrypt){
        byte[] encoded = Base64.getEncoder().encode(bytesToEncrypt);
        byte[] result = ByteBuffer.allocate(bytesToEncrypt.length + encoded.length).put(bytesToEncrypt).put(encoded).array();
        return result;
    }

    private User getLoggedInUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((User) principal);
        }
        return null;
    }
}
