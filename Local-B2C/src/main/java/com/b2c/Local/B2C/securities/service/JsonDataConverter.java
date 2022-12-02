package com.b2c.Local.B2C.securities.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Base64;

@ControllerAdvice
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
        return objectMapper.readValue(decrypt(inputMessage.getBody()), clazz);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody().write(encrypt(objectMapper.writeValueAsBytes(o)));
    }

    private InputStream decrypt(InputStream inputStream){
        return inputStream;
    }

    private byte[] encrypt(byte[] bytesToEncrypt){
        byte[] encoded = Base64.getEncoder().encode(bytesToEncrypt);
        byte[] result = ByteBuffer.allocate(bytesToEncrypt.length + encoded.length).put(bytesToEncrypt).put(encoded).array();
        return result;
    }
}
