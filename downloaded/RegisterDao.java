package com.example.handinapp.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RegisterDao {

    @Insert
    void insert(Register register);

    @Query("SELECT * FROM register_table")
    LiveData<List<Register>> getAllRegisters();
}
