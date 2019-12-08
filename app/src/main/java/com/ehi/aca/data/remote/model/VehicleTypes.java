package com.ehi.aca.data.remote.model;

/*
 * File Description
 * Author: Hardi
 */

public class VehicleTypes {
    private boolean IsPrimary;

    private String Name;

    public boolean isPrimary() {
        return IsPrimary;
    }

    public void setPrimary(boolean primary) {
        IsPrimary = primary;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
