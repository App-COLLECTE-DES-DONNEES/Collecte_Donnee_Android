package com.ditros.mcd.model;

import androidx.room.Entity;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(tableName = "personalcoholconsumption")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonAlcoholConsumption extends JournalData {
    private Long code;
    private String value;

}
