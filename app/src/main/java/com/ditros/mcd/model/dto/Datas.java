package com.ditros.mcd.model.dto;

import com.ditros.mcd.Dao.SeatingPlaceDAO;
import com.ditros.mcd.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Datas {

    private AccidentSeverity[] accidentSeverityResp;
    private AccidentType[] accidentTypeResp;

    private PersonAction[] actionResp;
    private VehicleAction[] vehicleActionResp;
    private PersonDrugUse[] personDrugUseResp;
    private PersonAlcoholConsumption[] alcoholConsumptionResp;
    private VehicleBrand[] brandResp;

    private AccidentBrightnessCondition[] brightnessConditionResp;
    private AccidentClimaticCondition[] climaticConditionResp;
    private PersonGender[] genderResp;
    private AccidentImpactType[] impactTypeResp;
    private Municipality[] municipalityResp;
    private OccupantRestraintSystem[] occupantRestraintSystemResp;
    private PersonRoadType[] personRoadTypeResp;
    private RoadBlock[] blockResp;
    private RoadCategory[] roadCategoryResp;
    private RoadIntersection[] roadIntersectionResp;
    private Road[] roadResp;
    private RoadSlopSection[] roadSlopSectionResp;
    private RoadState[] roadStateResp;
    private RoadType[] roadTypeResp;
    private WearingHelmet[] wearingHelmetResp;
    private Virage[] virageResp;
    private VehicleType[] vehicleTypeResp;
    private VehicleModel[] vehicleModelResp;
    private PersonTraumaSeverity[] traumaSeverityResp;
    private RoadTrafficControl[]  controlResp;
    private VehicleSpecialFunction[] specialFunctionResp;
    private AlcoholTestStatus[] alcoholTestStatusResp;
    private AlcoholTestType[] alcoholTestTypeResp;
    private AlcoholTestResult[] alcoholTestResultResp;
    private SeatingPlace[] seatingPlaceResp;
    private SeatingRange[] seatingRangeResp;
    private City[] cityResp;


}
