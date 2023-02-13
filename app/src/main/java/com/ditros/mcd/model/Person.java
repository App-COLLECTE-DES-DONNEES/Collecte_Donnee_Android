package com.ditros.mcd.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(tableName = "Person",
        foreignKeys = {
                @ForeignKey(entity = PersonGender.class,
                        parentColumns = "id",
                        childColumns = "persongender_id")
        },
        indices = {@Index(value = {"user_id"},
                unique = true)})
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Person extends JournalData {
    private String fullName;
    private String birthDateOms;
    private LocalDateTime birthDate;

    @ColumnInfo(name = "persongender_id")
    private String gender;



}
