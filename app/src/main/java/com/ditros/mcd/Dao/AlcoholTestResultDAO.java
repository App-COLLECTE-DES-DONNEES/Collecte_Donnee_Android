package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.AlcoholTestResult;

import java.util.List;

@Dao
public interface AlcoholTestResultDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertAlcoholTestResult(AlcoholTestResult alcoholTestResult);

    @Query("SELECT * FROM alcoholtestresult")
    List<AlcoholTestResult> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListAlcoholTestResult(AlcoholTestResult[] alcoholTestResults);


}
