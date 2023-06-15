package com.fpoly.ontapthihetmon;

import java.io.Serializable;

public class StudentModels implements Serializable {
    private String name;
    private int tuoi;

    public StudentModels(String name, int tuoi) {
        this.name = name;
        this.tuoi = tuoi;
    }
    public StudentModels(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }
}
