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

import java.util.List;

@Entity(tableName = "InsuranceAgent",
        foreignKeys = {
                @ForeignKey(entity = InsuranceCompany.class,
                        parentColumns = "id",
                        childColumns = "insurancecompany_id"),

                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "user_id")
        },
        indices = {@Index(value = {"user_id"},
                unique = true)})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InsuranceAgent  extends JournalData {
    private String matricule;
    private String phone;

    @ColumnInfo(name="user_id")
    private User user;

    @ColumnInfo(name="insurancecompany_id")
    private InsuranceCompany company;

}
