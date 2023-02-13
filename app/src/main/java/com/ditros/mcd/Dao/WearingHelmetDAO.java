package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.WearingHelmet;

import java.util.List;
@Dao
public interface WearingHelmetDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertWearingHelmetDAO(WearingHelmet WearingHelmet);

    @Query("SELECT * FROM WearingHelmet")
    List<WearingHelmet> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListWearingHelmetDAO(WearingHelmet[] WearingHelmets);
    
}
