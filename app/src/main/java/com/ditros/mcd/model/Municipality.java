package com.ditros.mcd.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.ditros.mcd.model.inherited.JournalData;
import lombok.*;
import java.util.List;

@Entity(tableName = "Municipality",
        foreignKeys = {
                @ForeignKey(entity = Department.class,
                        parentColumns = "id",
                        childColumns = "department_id")
        })

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class
Municipality extends JournalData {
    private String code;
    private String name;


    @ColumnInfo(name="department_id")
    private String department;

}
