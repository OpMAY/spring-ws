package com.interceptor;

import org.springframework.http.MediaType;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface LoggingInterceptor {
    public Map<String, Object> getHeaders(ContentCachingRequestWrapper request);

    String getPayload(String contentType, InputStream inputStream) throws IOException;

    public boolean isVisible(MediaType mediaType);

    public String getRequestBody(ContentCachingRequestWrapper request);

    public String getResponseBody(ContentCachingResponseWrapper response) throws IOException;

    public String getParameterMap(Map<String, String[]> parameterMap) throws IOException;
}
