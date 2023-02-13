package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.SeatingRange;

import java.util.List;

@Dao
public interface SeatingRangeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertSeatingRangeDAO(SeatingRange SeatingRange);

    @Query("SELECT * FROM SeatingRange")
    List<SeatingRange> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListSeatingRangeDAO(SeatingRange[] SeatingRanges);
    
}
