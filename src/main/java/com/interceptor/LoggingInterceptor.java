package com.interceptor;

import org.springframework.http.MediaType;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface LoggingInterceptor {
    public Map<String, Object> getHeaders(HttpServletRequest request);

    String getPayload(String contentType, InputStream inputStream) throws IOException;

    public boolean isVisible(MediaType mediaType);

    public String getRequestBody(ContentCachingRequestWrapper request);

    public String getResponseBody(final HttpServletResponse response) throws IOException;

    public String getParameterMap(Map<String, String[]> parameterMap) throws IOException;
}
