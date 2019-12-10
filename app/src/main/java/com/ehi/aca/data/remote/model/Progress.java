package com.ehi.aca.data.remote.model;

public class Progress {
    private String progressName;
    private boolean progressValue;

    public Progress(String progressName, boolean progressValue) {
        this.progressName = progressName;
        this.progressValue = progressValue;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

    public boolean getProgressValue() {
        return progressValue;
    }

    public void setProgressValue(boolean progressValue) {
        this.progressValue = progressValue;
    }
}
