package com.example.asus.foodorder.Model;

import io.realm.RealmObject;

public class HistoryPesanan extends RealmObject {
    String idPesanan, namaPemesan, mejaPemesan, namaMakanan;
    int id, totalPembayaran;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public void setNamaPemesan(String namaPemesan) {
        this.namaPemesan = namaPemesan;
    }

    public String getMejaPemesan() {
        return mejaPemesan;
    }

    public void setMejaPemesan(String mejaPemesan) {
        this.mejaPemesan = mejaPemesan;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public int getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(int totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }
}