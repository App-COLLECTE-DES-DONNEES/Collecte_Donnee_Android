package com.ditros.mcd.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "PersonAccident",

        foreignKeys = {

                @ForeignKey(entity = Accident.class,
                        childColumns = "accident_id",
                        parentColumns = "id"),

                @ForeignKey(entity = PersonRoadType.class,
                        childColumns = "personroadtype_id",
                        parentColumns = "id"),

                @ForeignKey(entity = PersonAlcoholConsumption.class,
                        childColumns = "personalcoholconsumption_id",
                        parentColumns = "id"),
                @ForeignKey(entity = AlcoholTestStatus.class,
                        childColumns = "alcoholteststatus_id",
                        parentColumns = "id"),
                @ForeignKey(entity = AlcoholTestType.class,
                        childColumns = "alcoholtesttype",
                        parentColumns = "id"),
                @ForeignKey(entity = AlcoholTestResult.class,
                        childColumns = "alcoholtestresult_id",
                        parentColumns = "id"),
                @ForeignKey(entity = PersonDrugUse.class,
                        childColumns = "personaldruguse_id",
                        parentColumns = "id"),
                @ForeignKey(entity = SeatingRange.class,
                        childColumns = "seatingrange_id",
                        parentColumns = "id"),
                @ForeignKey(entity = SeatingPlace.class,
                        childColumns = "seatingplace_id",
                        parentColumns = "id"),
                @ForeignKey(entity = PersonTraumaSeverity.class,
                        childColumns = "persontraumaseverity_id",
                        parentColumns = "id"),
                @ForeignKey(entity = PersonAction.class,
                        childColumns = "personaction_id",
                        parentColumns = "id"),
                @ForeignKey(entity = PersonSafetyEquipment.class,
                        childColumns = "personsafetyequipment_id",
                        parentColumns = "id"),
                @ForeignKey(entity = Person.class,
                        childColumns = "person_id",
                        parentColumns = "id")


        })
@Getter @Setter
public class PersonAccident extends JournalData {

    @ColumnInfo(name="person_number")
    private int personNumber;

    @ColumnInfo(name="vehicle_number")
    private int vehicleNumber;

    @ColumnInfo(name="vehicle_linked_pedestrian")
    private int vehicleLinkedPedestrian;

    @ColumnInfo(name="driving_licence_year")
    private String drivingLicenceYear;

    @ColumnInfo(name="age")
    private int age;


}
