package com.smk.cashier.model;

public class Stock extends Model {
    private int id;
    private String kode;
    private int stock;

    public int getId() {
        return id;
    }
    public void setId(int newId) {
        this.id = newId;
    }
    public String getKode() {
        return kode;
    }
    public void setKode(String newKode) {
        this.kode = newKode;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int newStock) {
        this.stock = newStock;
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
