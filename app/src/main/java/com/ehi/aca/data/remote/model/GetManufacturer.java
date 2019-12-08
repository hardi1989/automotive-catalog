package com.ehi.aca.data.remote.model;

import java.util.List;

/*
 * File Description
 * Author: Hardi
 */

public class GetManufacturer {
    private int Count;

    private String Message;

    private List<Manufacturer> Results;

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<Manufacturer> getResults() {
        return Results;
    }

    public void setResults(List<Manufacturer> results) {
        Results = results;
    }
}
