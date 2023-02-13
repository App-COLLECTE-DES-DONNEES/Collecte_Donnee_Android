package com.ditros.mcd.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.ditros.mcd.model.AccidentType;
import com.ditros.mcd.model.PersonAction;

import java.util.List;
@Dao
public interface PersonActionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertPersonAction(PersonAction personAction);

    @Query("SELECT * FROM personaction")
    List<PersonAction> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertListPersonAction(PersonAction[] personActions);

}
