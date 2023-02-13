package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.VehicleBrand;

import java.util.List;
@Dao
public interface VehiculeBrandDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertVehicleBrandDAO(VehicleBrand VehicleBrand);

    @Query("SELECT * FROM VehicleBrand")
    List<VehicleBrand> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListVehicleBrandDAO(VehicleBrand[] VehicleBrands);
    
}
