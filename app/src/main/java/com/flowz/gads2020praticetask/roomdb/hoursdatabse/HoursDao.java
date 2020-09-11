package com.flowz.gads2020praticetask.roomdb.hoursdatabse;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface HoursDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(dbHoursModel hoursModel);

    @Delete
    void delete(dbHoursModel hoursModel);

    @Query("SELECT * FROM hours_table")
    LiveData<List<dbHoursModel>>getAllIhours();


}
