package com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.json.JSONPropertyIgnore;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@JsonIgnoreProperties
@NoArgsConstructor
public class User {
    private int no;
    @NonNull
    private String email;
    @NonNull
    private String id;
    @NonNull
    private String name;

    private String grant = "general";
    private String access_token = "setting the password";
}
