package com.ditros.mcd.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity(tableName = "Accident",
        foreignKeys = {
            @ForeignKey(entity= AccidentType.class,
            childColumns = "accidenttype_id",
            parentColumns  = "id"),
        @ForeignKey(
                entity = Municipality.class,
                childColumns = "municipality_id",
                parentColumns = "id"
        ),
        @ForeignKey(entity= AccidentBrightnessCondition.class,
                childColumns = "accidentbrightnesscondition_id",
                parentColumns  = "id"),

        @ForeignKey(entity= AccidentClimaticCondition.class,
                childColumns = "accidentclimaticcondition_id",
                parentColumns  = "id"),
        @ForeignKey(entity= AccidentSeverity.class,
                childColumns = "accidentseverity_id",
                parentColumns  = "id"),
        @ForeignKey(entity= AccidentImpactType.class,
                childColumns = "accidentimpacttype_id",
                parentColumns  = "id"),
        @ForeignKey(entity= Road.class,
                childColumns = "road_id",
                parentColumns  = "id"),
        @ForeignKey(entity= RoadType.class,
                childColumns = "roadtype_id",
                parentColumns  = "id"),
        @ForeignKey(entity= RoadCategory.class,
                childColumns = "roadcategory_id",
                parentColumns  = "id"),
        @ForeignKey(entity= RoadBlock.class,
                childColumns = "roadblock_id",
                parentColumns  = "id"),
        @ForeignKey(entity= RoadState.class,
                childColumns = "roadstate_id",
                parentColumns  = "id"),
        @ForeignKey(entity= RoadIntersection.class,
                childColumns = "roadintersection_id",
                parentColumns  = "id"),
        @ForeignKey(entity= RoadTrafficControl.class,
                childColumns = "roadintersection_id",
                parentColumns  = "id"),
        @ForeignKey(entity= RoadSlopSection.class,
                childColumns = "roadslopsection_id",
                parentColumns  = "id"),
        @ForeignKey(entity= Virage.class,
                childColumns = "virage_id",
                parentColumns  = "id")
        }

        )
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Accident extends JournalData {

    @NonNull
    private String crashDate;

    @NonNull
    private String crashTime;

    @NonNull
    private String place;



    @ColumnInfo(name="accidenttype_id")
    private String typeAccident;

    @ColumnInfo(name="municipality_id")
    private String municipality;

    @ColumnInfo(name="accidentbrightnesscondition_id")
    private String brightnessCondition;

    @ColumnInfo(name="accidentclimaticcondition_id")
    private String climaticCondition;

    @ColumnInfo(name="roadtrafficcontrol_id")
    private String trafficControl;

    @ColumnInfo(name="accidentseverity_id")
    private String accidentSeverity;
    @ColumnInfo(name="accidentimpacttype_id")
    private String impactType;

    //Road
    @ColumnInfo(name="road_id")
    private String road;
    @ColumnInfo(name="roadtype_id")
    private String typeRoute;

    @ColumnInfo(name="roadcategory_id")
    private String roadCategory;


    @NonNull
    @ColumnInfo(name="roadblock_id")
    private String block;

    @ColumnInfo(name="roadstate_id")
    private String roadState;

    @ColumnInfo(name="roadintersection_id")
    private String roadIntersection;


    @ColumnInfo(name="virage_id")
    private String virage;

    @ColumnInfo(name="roadslopsection_id")
    private String roadSlopSection;




}
