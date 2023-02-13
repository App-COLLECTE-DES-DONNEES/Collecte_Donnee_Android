package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.VehicleAction;

import java.util.List;

@Dao
public interface VehicleActionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertVehicleActionDAO(VehicleAction VehicleAction);

    @Query("SELECT * FROM VehicleAction")
    List<VehicleAction> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListVehicleActionDAO(VehicleAction[] VehicleActions);
    
}
