package com.main.casinoapp;

import java.io.Serializable;

public class LoginData implements Serializable {
    String em;

    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public LoginData(String em, String pas) {
        this.em = em;
        this.pas = pas;
    }

    String pas;
}
