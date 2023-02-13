package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.RoadTrafficControl;

import java.util.List;
@Dao
public interface RoadTrafficControlDAO {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertRoadTrafficControlDAO(RoadTrafficControl RoadTrafficControl);

    @Query("SELECT * FROM RoadTrafficControl")
    List<RoadTrafficControl> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListRoadTrafficControlDAO(RoadTrafficControl[] RoadTrafficControls);
    
    
}
