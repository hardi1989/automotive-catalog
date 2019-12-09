package com.ehi.aca.data.remote.model;

import java.util.List;

/*
 * File Description
 * Author: Hardi
 */

public class GetModel {
    private int Count;

    private String Message;

    private List<VModel> Results;

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

    public List<VModel> getResults() {
        return Results;
    }

    public void setResults(List<VModel> results) {
        Results = results;
    }
}
