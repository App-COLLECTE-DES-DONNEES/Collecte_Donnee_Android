package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.RoadType;

import java.util.List;
@Dao
public interface RoadTypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertRoadTypeDAO(RoadType RoadType);

    @Query("SELECT * FROM RoadType")
    List<RoadType> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListRoadTypeDAO(RoadType[] RoadTypes);
    
}
