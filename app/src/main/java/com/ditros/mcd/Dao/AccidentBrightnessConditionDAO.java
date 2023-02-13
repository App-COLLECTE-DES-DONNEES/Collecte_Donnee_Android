package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.AccidentBrightnessCondition;

import java.util.List;

@Dao
public interface AccidentBrightnessConditionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertAccidentBrightnessCondition(AccidentBrightnessCondition accidentBrightnessCondition);

    @Query("SELECT * FROM accident_brightness_condition")
    List<AccidentBrightnessCondition> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListAccidentBrightnessCondition(AccidentBrightnessCondition[] accidentBrightnessCondition);

}
