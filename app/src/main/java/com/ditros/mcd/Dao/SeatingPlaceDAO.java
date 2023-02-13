package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.SeatingPlace;

import java.util.List;

@Dao
public interface SeatingPlaceDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertSeatingPlaceDAO(SeatingPlace SeatingPlace);

    @Query("SELECT * FROM SeatingPlace")
    List<SeatingPlace> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListSeatingPlaceDAO(SeatingPlace[] SeatingPlaces);
}
