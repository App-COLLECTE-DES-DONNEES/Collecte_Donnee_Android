package com.ditros.mcd.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private Long id;
    private String place;
    private String severity;
    private String crashDate;
    private int personInvolved;
    private int inCare;
    private String city;
    private String officerName;

    private String status;
}
