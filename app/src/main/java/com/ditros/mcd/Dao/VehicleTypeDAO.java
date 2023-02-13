package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.VehicleType;

import java.util.List;
@Dao
public interface VehicleTypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertVehicleTypeDAO(VehicleType VehicleType);

    @Query("SELECT * FROM VehicleType")
    List<VehicleType> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListVehicleTypeDAO(VehicleType[] VehicleTypes);
    
}
