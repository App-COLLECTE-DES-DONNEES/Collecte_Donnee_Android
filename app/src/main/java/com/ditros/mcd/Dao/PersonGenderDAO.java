package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.AccidentType;
import com.ditros.mcd.model.PersonGender;

import java.util.List;
@Dao
public interface PersonGenderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertPersonGenderDAO(PersonGender personGender);

    @Query("SELECT * FROM persongender")
    List<PersonGender> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListPersonGenderDAO(PersonGender[] personGenders);

}
