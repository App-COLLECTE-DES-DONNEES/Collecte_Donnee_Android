package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.VehicleSpecialFunction;

import java.util.List;
@Dao
public interface VehicleSpecialFunctionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertVehicleSpecialFunctionDAO(VehicleSpecialFunction VehicleSpecialFunction);

    @Query("SELECT * FROM VehicleSpecialFunction")
    List<VehicleSpecialFunction> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListVehicleSpecialFunctionDAO(VehicleSpecialFunction[] VehicleSpecialFunctions);
    
}
