package com.ditros.mcd.model.dto;

import com.ditros.mcd.model.AccidentClimaticCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonResp {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDateOms;
    private String birthDate;
    private AccidentClimaticCondition gender;
}