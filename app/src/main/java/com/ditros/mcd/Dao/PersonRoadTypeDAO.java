package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.PersonRoadType;

import java.util.List;
@Dao
public interface PersonRoadTypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertPersonRoadType(PersonRoadType personRoadType);

    @Query("SELECT * FROM PersonRoadType")
    List<PersonRoadType> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListPersonRoadType(PersonRoadType[] personRoadTypes);
    
}
