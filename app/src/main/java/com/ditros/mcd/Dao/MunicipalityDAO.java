package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.Municipality;

import java.util.List;
@Dao
public interface MunicipalityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertMunicipality(Municipality municipality);

    @Query("SELECT * FROM Municipality")
    List<Municipality> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListMunicipality(Municipality[] municipalities);

}
