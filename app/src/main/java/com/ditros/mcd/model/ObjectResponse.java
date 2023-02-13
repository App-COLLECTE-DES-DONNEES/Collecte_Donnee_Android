package com.ditros.mcd.model;


import com.ditros.mcd.model.dto.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObjectResponse {

    private Data data;
    private String message;
    private Boolean success;
    private int status;
}
