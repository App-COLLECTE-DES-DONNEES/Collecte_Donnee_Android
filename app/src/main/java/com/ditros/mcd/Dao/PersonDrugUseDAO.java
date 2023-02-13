package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.PersonDrugUse;

import java.util.List;

@Dao
public interface PersonDrugUseDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertPersonDrugUse(PersonDrugUse personDrugUse);


    @Query("SELECT * FROM persondruguse")
    List<PersonDrugUse> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListPersonDrugUse(PersonDrugUse[] personDrugUses);
    
}
