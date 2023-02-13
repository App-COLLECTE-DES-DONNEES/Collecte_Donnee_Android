package com.ditros.mcd.model;

import com.ditros.mcd.model.dto.Data;
import com.ditros.mcd.model.dto.Data1;
import com.ditros.mcd.model.dto.Datas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObjectResponse1 {

    private Data1 data;
    private String message;
    private Boolean success;
    private int status;

}
