package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.Virage;

import java.util.List;
@Dao
public interface VirageDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertVirageDAO(Virage Virage);

    @Query("SELECT * FROM Virage")
    List<Virage> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListVirageDAO(Virage[] Virages);
    
}
