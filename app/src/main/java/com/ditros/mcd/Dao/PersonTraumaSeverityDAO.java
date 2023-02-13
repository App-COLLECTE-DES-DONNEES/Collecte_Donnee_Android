package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.PersonTraumaSeverity;

import java.util.List;
@Dao
public interface PersonTraumaSeverityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertPersonTraumaSeverityDAO(PersonTraumaSeverity PersonTraumaSeverity);

    @Query("SELECT * FROM persontraumaseverity")
    List<PersonTraumaSeverity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListPersonTraumaSeverityDAO(PersonTraumaSeverity[] PersonTraumaSeveritys);
    
}
