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

@Entity(tableName = "Gendarme",
        foreignKeys = {
                @ForeignKey(entity = Gendarmerie.class,
                        parentColumns = "id",
                        childColumns = "gendarmerie_id"),
                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "user_id")
        },
        indices = {@Index(value = {"user_id"},
                unique = true)})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Gendarme extends JournalData {

    private String matricule;

    @ColumnInfo(name="user_id")
    private User user;

    @ColumnInfo(name="gendarmerie_id")
    private String gendarmerie;



}
