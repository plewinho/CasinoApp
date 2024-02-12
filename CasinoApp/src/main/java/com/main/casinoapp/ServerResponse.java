package com.main.casinoapp;

import java.io.Serial;
import java.io.Serializable;

public class ServerResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Serializable data;

    public ServerResponse() {
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }
}