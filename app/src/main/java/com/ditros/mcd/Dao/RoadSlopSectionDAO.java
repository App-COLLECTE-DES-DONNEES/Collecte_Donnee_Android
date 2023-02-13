package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.RoadSlopSection;

import java.util.List;
@Dao
public interface RoadSlopSectionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertRoadSlopSectionDAO(RoadSlopSection RoadSlopSection);

    @Query("SELECT * FROM RoadSlopSection")
    List<RoadSlopSection> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListRoadSlopSectionDAO(RoadSlopSection[] RoadSlopSections);
    
}
