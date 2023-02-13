package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.RoadBlock;

import java.util.List;
@Dao
public interface RoadBlockDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertRoadBlockDAO(RoadBlock RoadBlock);

    @Query("SELECT * FROM RoadBlock")
    List<RoadBlock> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListRoadBlockDAO(RoadBlock[] RoadBlocks);
    
}
