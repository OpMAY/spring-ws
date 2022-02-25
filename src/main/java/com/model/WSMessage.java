package com.model;

import com.model.common.MFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WSMessage {

    private Long id;
    private String message;
    private String username;
    private String timestamp;
    private String role;

    private MFile file;
}
