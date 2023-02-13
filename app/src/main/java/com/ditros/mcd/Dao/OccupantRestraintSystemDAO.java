package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.AccidentType;
import com.ditros.mcd.model.OccupantRestraintSystem;

import java.util.List;
@Dao
public interface OccupantRestraintSystemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertOccupantRestraintSystem(OccupantRestraintSystem occupantRestraintSystem);

    @Query("SELECT * FROM occupantrestraintsystem")
    List<OccupantRestraintSystem> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListOccupantRestraintSystem(OccupantRestraintSystem[] occupantRestraintSystems);
    
}
