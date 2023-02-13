package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.AlcoholTestStatus;

import java.util.List;

@Dao
public interface AlcoholTestStatusDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertAlcoholTestStatus(AlcoholTestStatus alcoholTestStatus);

    @Query("SELECT * FROM alcoholteststatus")
    List<AlcoholTestStatus> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListAlcoholTestStatus(AlcoholTestStatus[] alcoholTestStatuses);
    
}
