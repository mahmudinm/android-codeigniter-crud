package com.example.mahmudinm.androidcodeignitercrud.model;

import java.io.Serializable;

/**
 * Created by Mahmudinm on 20/12/2018.
 */

public class Item implements Serializable {

    String id, nama, harga;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
