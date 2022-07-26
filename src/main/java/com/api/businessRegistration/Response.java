package com.api.businessRegistration;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Response {

    private Integer request_cnt;
    private Integer match_cnt;
    private String status_code;
    private List<Data> data;
}
