package com.ditros.mcd.model.dto;


import lombok.*;
import lombok.Data;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccidentReq {

    private Long id;
    private String accidentDate; //JJMMAAAA
    private String accidentTime;
    private Long municipality;
    private String place;
    private Long road;
    private Long roadType;
    private Long roadCategory;
    private int speedLimit;
    private Long block;
    private Long roadState;
    private Long roadIntersection;
    private Long roadTrafficControl;
    private Long virage;
    private Long roadSlopSection;
    private Long accidentType;
    private Long impactType;
    private Long climaticCondition;
    private Long brightnessCondition;
    private Long accidentSeverity;
    private Long city;
    private List<VehiculeReq> vehicules;
    private List<PersonDtoReq> persons;


}
