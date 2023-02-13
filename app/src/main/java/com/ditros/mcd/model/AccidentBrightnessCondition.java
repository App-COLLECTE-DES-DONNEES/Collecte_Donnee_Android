package com.ditros.mcd.model;

import androidx.room.Entity;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Entity(tableName = "accident_brightness_condition")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AccidentBrightnessCondition extends JournalData {

    private Long code;
    private String value;

   /* public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }*/
}
