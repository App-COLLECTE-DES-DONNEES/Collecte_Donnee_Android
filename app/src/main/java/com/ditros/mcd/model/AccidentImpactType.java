package com.ditros.mcd.model;

import androidx.room.Entity;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccidentImpactType extends JournalData {

    private Long code;
    private String value;


}
