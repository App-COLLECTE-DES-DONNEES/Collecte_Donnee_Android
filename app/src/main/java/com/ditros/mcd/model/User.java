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


@Entity(
        foreignKeys = {
        @ForeignKey(entity = Gendarme.class,
                        childColumns = "gendarme_id",
                        parentColumns = "id"),
        @ForeignKey(entity = Person.class,
                childColumns = "person_id",
                parentColumns = "id"),
        @ForeignKey(entity = PoliceOfficer.class,
                childColumns = "police_officer_id",
                parentColumns = "id")
        },
        indices = {@Index(value = {"gendarme_id", "police_officer_id","person_id"},
                unique = true)})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends JournalData {
    private String keycloakId;

    @ColumnInfo(name="gendarme_id")
    private Gendarme gendarme ;

    @ColumnInfo(name="police_officer_id")
    private Person person;

    @ColumnInfo(name="police_officer_id")
    private PoliceOfficer policeOfficer;



}
