package com.ehi.aca.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ehi.aca.data.local.entity.ManufacturerEntity;

import java.util.List;

/*
 * File Description
 * Author: Hardi
 */

@Dao
public interface ManufacturerDao {
    @Insert
    void insert(ManufacturerEntity manufacturerEntity);

    @Delete
    void delete(ManufacturerEntity manufacturerEntity);

    @Update
    void update(ManufacturerEntity manufacturerEntity);

    @Query("DELETE FROM manufacturer_table")
    void deleteAll();

    @Query("SELECT * FROM manufacturer_table ORDER BY id")
    LiveData<List<ManufacturerEntity>> getAllManufacturer();
}
