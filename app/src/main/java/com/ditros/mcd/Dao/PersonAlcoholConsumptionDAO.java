package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.PersonAlcoholConsumption;

import java.util.List;
@Dao
public interface PersonAlcoholConsumptionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertPersonAlcoholConsumption(PersonAlcoholConsumption personAlcoholConsumption);

    @Query("SELECT * FROM PersonAlcoholConsumption")
    List<PersonAlcoholConsumption> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListPersonAlcoholConsumption(PersonAlcoholConsumption[] personAlcoholConsumptions);
    
}
