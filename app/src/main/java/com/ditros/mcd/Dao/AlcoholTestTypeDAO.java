package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.AlcoholTestType;

import java.util.List;

@Dao
public interface AlcoholTestTypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertAlcoholTestType(AlcoholTestType alcoholTestType);

    @Query("SELECT * FROM alcoholtesttype")
    List<AlcoholTestType> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListAlcoholTestType(AlcoholTestType[] alcoholTestTypes);

}


