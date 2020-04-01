package com.android.sensortanah;

public class Tanaman {
    String namaTanaman;
    Integer on;
    Integer off;
        public Tanaman(){

        }

    public Tanaman(String id, String namaTanaman, Integer on, Integer off) {
        this.namaTanaman = namaTanaman;
        this.on = on;
        this.off = off;
    }

    public String getNamaTanaman() {
        return namaTanaman;
    }

    public Integer getOn() {
        return on;
    }

    public Integer getOff() {
        return off;
    }
}



