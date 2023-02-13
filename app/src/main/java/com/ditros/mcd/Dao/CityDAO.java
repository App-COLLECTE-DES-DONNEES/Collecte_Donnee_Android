package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.City;

import java.util.List;

@Dao
public interface CityDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertCitySeverity(City city);

    @Query("SELECT * FROM City")
    List<City> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListCitySeverity(City[] City);
    
}
