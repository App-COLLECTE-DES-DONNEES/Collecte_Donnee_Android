package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.AccidentClimaticCondition;

import java.util.List;

@Dao
public interface AccidentClimaticConditionDAO {
    
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertAccidentClimaticCondition(AccidentClimaticCondition AccidentClimaticCondition);
    
    @Query("SELECT * FROM accident_climatic_condition")
    List<AccidentClimaticCondition> getAll();
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListAccidentClimaticCondition(AccidentClimaticCondition[] AccidentClimaticCondition);
    
}
