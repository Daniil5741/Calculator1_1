package com.example.calculator1_1;

import java.io.Serializable;

public class Answer implements Serializable {
    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    private String ans;
}
