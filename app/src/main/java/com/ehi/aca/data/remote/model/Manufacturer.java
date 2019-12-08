package com.ehi.aca.data.remote.model;

import java.util.List;

/*
 * File Description
 * Author: Hardi
 */

public class Manufacturer {

    private String Country;

    private String Mfr_CommonName;

    private int Mfr_ID;

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getMfr_CommonName() {
        return Mfr_CommonName;
    }

    public void setMfr_CommonName(String mfr_CommonName) {
        Mfr_CommonName = mfr_CommonName;
    }

    public int getMfr_ID() {
        return Mfr_ID;
    }

    public void setMfr_ID(int mfr_ID) {
        Mfr_ID = mfr_ID;
    }

    public String getMfr_Name() {
        return Mfr_Name;
    }

    public void setMfr_Name(String mfr_Name) {
        Mfr_Name = mfr_Name;
    }

    public List<com.ehi.aca.data.remote.model.VehicleTypes> getVehicleTypes() {
        return VehicleTypes;
    }

    public void setVehicleTypes(List<com.ehi.aca.data.remote.model.VehicleTypes> vehicleTypes) {
        VehicleTypes = vehicleTypes;
    }

    private String Mfr_Name;

    private List<com.ehi.aca.data.remote.model.VehicleTypes> VehicleTypes;
}
