package com.flowz.gads2020praticetask.roomdb.skilliqdatabase;

import com.flowz.gads2020praticetask.roomdb.skilliqdatabase.dbSkilliqModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface SkillIqDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(dbSkilliqModel skilliqModel);

    @Delete
    void delete (dbSkilliqModel skilliqModel);

    @Query("SELECT * FROM skilliq_table")
    LiveData<List<dbSkilliqModel>>getAllIqScores();


}
