package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.VehicleModel;

import java.util.List;
@Dao
public interface VehicleModelDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertVehicleModelDAO(VehicleModel VehicleModel);

    @Query("SELECT * FROM VehicleModel")
    List<VehicleModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListVehicleModelDAO(VehicleModel[] VehicleModels);
    
}
