package com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.common.TimeRecordable;
import lombok.*;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@JsonIgnoreProperties
@NoArgsConstructor
public class User extends TimeRecordable {
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
