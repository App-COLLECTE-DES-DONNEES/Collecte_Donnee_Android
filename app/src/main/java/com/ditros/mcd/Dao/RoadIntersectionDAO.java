package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.RoadIntersection;

import java.util.List;
@Dao
public interface RoadIntersectionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertRoadIntersectionDAO(RoadIntersection RoadIntersection);

    @Query("SELECT * FROM RoadIntersection")
    List<RoadIntersection> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListRoadIntersectionDAO(RoadIntersection[] RoadIntersections);
}
