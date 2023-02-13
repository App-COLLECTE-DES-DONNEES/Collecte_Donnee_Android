package com.ditros.mcd.model.dto;

import com.ditros.mcd.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonAccidentResp {
    private Long id;
    private int personNumber;
    private int vehicleNumber;
    private int vehicleLinkedPedestrian;
    private String drivingLicenceYear;
    private int age;
    private PersonRoadType roadType;
    private PersonAlcoholConsumption alcoholConsumption;
    private AlcoholTestStatus testStatus;
    private AlcoholTestType testType;
    private AlcoholTestResult testResult;
    private PersonDrugUse drugUse;
    private SeatingRange range;
    private SeatingPlace place;
    private PersonTraumaSeverity traumaSeverity;
    private PersonAction action;
    private WearingHelmet wearingHelmet;
    private OccupantRestraintSystem occupantRestraintSystem;
    private PersonResp person;
}
