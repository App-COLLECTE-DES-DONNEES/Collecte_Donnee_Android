package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.RoadState;

import java.util.List;
@Dao
public interface RoadStateDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertRoadStateDAO(RoadState RoadState);

    @Query("SELECT * FROM RoadState")
    List<RoadState> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListRoadStateDAO(RoadState[] RoadStates);
    
}
