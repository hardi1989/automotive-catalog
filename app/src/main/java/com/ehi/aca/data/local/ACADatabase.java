package com.ehi.aca.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ehi.aca.data.local.dao.ManufacturerDao;
import com.ehi.aca.data.local.entity.ManufacturerEntity;

/*
 * File Description
 * Author: Hardi
 */

@Database(entities = {ManufacturerEntity.class}, version = 1)
public abstract class ACADatabase extends RoomDatabase {

    private static ACADatabase instance;

    public abstract ManufacturerDao manufacturerDao();

    public static synchronized ACADatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ACADatabase.class, "aca_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
