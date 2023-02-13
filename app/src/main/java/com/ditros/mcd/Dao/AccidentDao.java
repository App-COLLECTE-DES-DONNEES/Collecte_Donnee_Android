package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.Accident;

import java.util.List;
@Dao
public interface AccidentDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertAccidentSeverity(Accident Accident);
    
    @Query("SELECT * FROM Accident")
    List<Accident> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListAccidentSeverity(Accident[] Accident);
    
}
