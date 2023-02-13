package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.Road;

import java.util.List;
@Dao
public interface RoadDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertRoadDAO(Road Road);

    @Query("SELECT * FROM Road")
    List<Road> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListRoadDAO(Road[] Roads);
    
}
