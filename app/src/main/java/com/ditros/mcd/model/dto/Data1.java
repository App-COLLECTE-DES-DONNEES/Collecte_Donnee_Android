package com.ditros.mcd.model.dto;


import com.ditros.mcd.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Data1 {

    private Long id;
    private String accidentDate; //JJMMAAAA
    private String accidentTime;
    private String municipality;
    private String place;
    private Long latitude;
    private Long longitude;

    private Road road;
    private RoadType roadType;
    private RoadCategory  roadCategory;

    private int speedLimit;

    private RoadBlock block;
    private RoadState roadState;
    private RoadIntersection roadIntersection;
    private RoadTrafficControl  roadTrafficControl;
    private Virage virage;
    private RoadSlopSection roadSlopSection;
    private AccidentType accidentType;
    private AccidentImpactType impactType;
    private AccidentClimaticCondition climaticCondition;
    private AccidentBrightnessCondition brightnessCondition;
    private AccidentSeverity accidentSeverity;
    private City city;
    private VehicleAccidentResp[] vehicules;
    private PersonAccidentResp[] persons;
}
