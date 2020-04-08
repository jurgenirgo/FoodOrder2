package com.example.asus.foodorder.Model;

public class FoodDataModel {
    String namaMakanan, gambar;
    int totalItem, harga, totalHarga;

    public FoodDataModel(String namaMakanan, String gambar, int totalItem, int harga, int totalHarga) {
        this.namaMakanan = namaMakanan;
        this.gambar = gambar;
        this.totalItem = totalItem;
        this.harga = harga;
        this.totalHarga = totalHarga;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public String getGambar() {
        return gambar;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public int getHarga() {
        return harga;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
}
