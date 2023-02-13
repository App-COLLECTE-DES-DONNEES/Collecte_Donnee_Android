package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.AccidentType;

import java.util.List;
@Dao
public interface AccidentTypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertAccidentTypeDAO(AccidentType accidentTypeDAO);

    @Query("SELECT * FROM accident_type")
    List<AccidentType> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListAccidentTypeDAO(AccidentType[] accidentTypeDAOS);
    
}
