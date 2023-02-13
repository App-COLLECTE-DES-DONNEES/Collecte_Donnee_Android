package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.RoadCategory;

import java.util.List;
@Dao
public interface RoadCategoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertRoadCategoryDAO(RoadCategory RoadCategory);

    @Query("SELECT * FROM roadcategory")
    List<RoadCategory> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListRoadCategoryDAO(RoadCategory[] RoadCategorys);
    
}
