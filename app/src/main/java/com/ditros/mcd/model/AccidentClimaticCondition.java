package com.ditros.mcd.model;

import androidx.room.Entity;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(tableName = "accident_climatic_condition")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AccidentClimaticCondition extends JournalData {

    private Long code;
    private String value;




}
