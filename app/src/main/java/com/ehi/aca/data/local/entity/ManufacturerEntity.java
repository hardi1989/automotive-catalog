package com.ehi.aca.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/*
 * File Description
 * Author: Hardi
 */

@Entity(tableName = "manufacturer_table")
public class ManufacturerEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("Mfr_ID")
    private int Mfr_Id;

    @SerializedName("Mfr_Name")
    private String Mfr_Name;

    public ManufacturerEntity(int Mfr_Id, String Mfr_Name) {
        this.Mfr_Id = Mfr_Id;
        this.Mfr_Name = Mfr_Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMfr_Id() {
        return Mfr_Id;
    }

    public String getMfr_Name() {
        return Mfr_Name;
    }

    public void setMfr_Id(int mfr_Id) {
        Mfr_Id = mfr_Id;
    }

    public void setMfr_Name(String mfr_Name) {
        Mfr_Name = mfr_Name;
    }
}
