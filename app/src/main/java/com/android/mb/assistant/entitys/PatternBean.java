package com.android.mb.assistant.entitys;

import java.io.Serializable;

public class PatternBean implements Serializable {

    /**
     * mPatternId : 1
     * mPatternName : FWQEF22
     * pageNo : 1
     * pageSize : 10
     */

    private String mPatternId;
    private String mPatternName;

    public String getPatternId() {
        return mPatternId == null ? "" : mPatternId;
    }

    public void setPatternId(String patternId) {
        mPatternId = patternId;
    }

    public String getPatternName() {
        return mPatternName == null ? "" : mPatternName;
    }

    public void setPatternName(String patternName) {
        mPatternName = patternName;
    }
}
