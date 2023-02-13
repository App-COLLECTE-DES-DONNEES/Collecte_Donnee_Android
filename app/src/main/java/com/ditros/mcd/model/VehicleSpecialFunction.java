package com.ditros.mcd.model;

import androidx.room.Entity;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class VehicleSpecialFunction extends JournalData {
    private String code;
    private String value;
}
