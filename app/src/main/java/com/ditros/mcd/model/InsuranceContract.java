package com.ditros.mcd.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(tableName = "InsuranceContract",
        foreignKeys = {
                @ForeignKey(entity = Vehicle.class,
                        parentColumns = "id",
                        childColumns = "vehicle_id"),
                @ForeignKey(entity = InsuranceAgent.class,
                        parentColumns = "id",
                        childColumns = "insuranceagent_id")
        })
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class InsuranceContract extends JournalData {
    private String contractNumber;
    private String name;
    private String subscriber;
    private String pf;
    private double primeAssurance;
    private String numberPlate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ColumnInfo(name="vehicle_id")
    private Vehicle vehicle;

    @ColumnInfo(name="insuranceagent_id")
    private InsuranceAgent agent;
}
