package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.AccidentImpactType;

import java.util.List;

@Dao
public interface AccidentImpactTypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertAccidentImpactType(AccidentImpactType accidentImpactType);

    @Query("SELECT * FROM AccidentImpactType")
    List<AccidentImpactType> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListAccidentImpactType(AccidentImpactType[] AccidentImpactType);
    
}
