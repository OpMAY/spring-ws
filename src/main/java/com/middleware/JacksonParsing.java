package com.middleware;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import lombok.extern.log4j.Log4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

@Log4j
public class JacksonParsing {
    private static String CHARSET = "utf-8";

    public static <T> T toObject(String json) {
        T result = null;
        log.info("toObject" + json);
        try {
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.readValue(json, new TypeReference<T>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String toString(Object object) {
        return toString(object, CHARSET);
    }

    public static String toString(Object object, String charset) {
        ByteArrayOutputStream output = null;
        Writer write = null;
        String data = null;

        try {
            output = new ByteArrayOutputStream();
            write = new OutputStreamWriter(output, charset);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(write, object);
            data = output.toString(charset);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (output != null) try {
                output.close();
            } catch (IOException e) {
            }
            if (write != null) try {
                write.close();
            } catch (IOException e) {
            }
        }

        return data;
    }
}
