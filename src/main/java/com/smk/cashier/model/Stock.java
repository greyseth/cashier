package com.smk.cashier.model;

public class Stock extends Model {
    private int id;
    private String kode;
    private int stock;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getKode() {
        return kode;
    }
    public void setKode(String kode) {
        this.kode = kode;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", kode='" + kode + '\'' +
                ", stock=" + stock +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", creator='" + creator + '\'' +
                ", updater='" + updater + '\'' +
                '}';
    }
}
