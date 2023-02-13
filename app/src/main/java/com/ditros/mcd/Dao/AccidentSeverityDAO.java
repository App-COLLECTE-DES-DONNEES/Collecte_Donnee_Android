package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.AccidentSeverity;

import java.util.List;

@Dao
public interface AccidentSeverityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertAccidentSeverity(AccidentSeverity accidentSeverity);

    @Query("SELECT * FROM accident_severity")
    List<AccidentSeverity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListAccidentSeverity(AccidentSeverity[] accidentSeverity);

}
